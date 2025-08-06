package com.mcgeecahill.starsongs.webui.ccontrollers;

import com.mcgeecahill.starsongs.songdata.dto.ArtistDto;
import com.mcgeecahill.starsongs.songdata.dto.SongDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UiController {

    private static final Logger log = LoggerFactory.getLogger(UiController.class);
    private static final String title = "Star Songs";

    @Autowired
    public RestTemplate restTemplate;

    @GetMapping(value = {"/", "/index"})
    public ModelAndView index() {
        final List<SongDto> songs = getAllSongs();
        final Set<Integer> artistIds = songs.stream()
                .map(SongDto::getArtistId).collect(Collectors.toSet());
        final Map<Integer, ArtistDto> artistMapById = getArtists(artistIds);
        return new ModelAndView(
                "index",
                Map.of(
                        "pageTitle", title,
                        "songs", songs,
                        "artistMapById", artistMapById
                )
        );
    }

    private List<SongDto> getAllSongs() {
        ResponseEntity<List<SongDto>> response = restTemplate.exchange("http://localhost:8086/v1/songs", HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
        log.debug("Song list: {}", response.getBody());
        return response.getBody();
    }


    private Map<Integer, ArtistDto> getArtists(final Set<Integer> artistIds) {
        final Map<Integer, ArtistDto> artistMapById = new HashMap<>();
        for (final Integer id : artistIds) {
            if (id != null) {
                try {
                    final ResponseEntity<ArtistDto> artistResp = restTemplate.exchange(
                            "http://localhost:8086/v1/artists/" + id,
                            HttpMethod.GET, null, ArtistDto.class);
                    artistMapById.put(id, artistResp.getBody());
                } catch (final HttpClientErrorException e) {
                    log.warn("http://localhost:8086/v1/artists/{} returns HTTP status {}", id, e.getStatusCode());
                    log.warn(e.getResponseBodyAsString());
                }
            }
        }

        return artistMapById;
    }

}
