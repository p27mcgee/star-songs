package com.mcgeecahill.starsongs.webui.ccontrollers;

import com.mcgeecahill.starsongs.songdata.dto.ArtistDto;
import com.mcgeecahill.starsongs.songdata.dto.SongDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * API Controller that proxies requests to the songdata service.
 * This acts as a Backend-for-Frontend (BFF) layer.
 */
@RestController
@RequestMapping("/api")
public class UiController {

    private static final Logger log = LoggerFactory.getLogger(UiController.class);

    @Value("${songdata.host}")
    private String songdataHost;

    @Value("${songdata.port}")
    private String songdataPort;

    @Value("${songdata.songs.path}")
    private String songsPath;

    @Value("${songdata.artists.path}")
    private String artistsPath;

    @Autowired
    public RestTemplate restTemplate;

    /**
     * Proxy endpoint to get all songs from the songdata service
     */
    @GetMapping("/songs")
    public List<SongDto> getAllSongs() {
        final String songsUrl = "http://" + songdataHost + ":" + songdataPort + songsPath;
        log.info("Proxying request to: {}", songsUrl);

        ResponseEntity<List<SongDto>> response = restTemplate.exchange(
                songsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SongDto>>() {}
        );

        log.debug("Song list: {}", response.getBody());
        return response.getBody();
    }

    /**
     * Proxy endpoint to get a specific artist by ID from the songdata service
     */
    @GetMapping("/artists/{id}")
    public ArtistDto getArtist(@PathVariable Integer id) {
        final String artistUrl = "http://" + songdataHost + ":" + songdataPort + artistsPath + "/" + id;
        log.info("Proxying request to: {}", artistUrl);

        ResponseEntity<ArtistDto> response = restTemplate.exchange(
                artistUrl,
                HttpMethod.GET,
                null,
                ArtistDto.class
        );

        log.debug("Artist: {}", response.getBody());
        return response.getBody();
    }

}
