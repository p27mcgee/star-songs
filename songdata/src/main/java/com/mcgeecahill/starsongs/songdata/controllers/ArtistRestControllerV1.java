package com.mcgeecahill.starsongs.songdata.controllers;

import com.mcgeecahill.starsongs.songdata.domain.Artist;
import com.mcgeecahill.starsongs.songdata.dto.ArtistDto;
import com.mcgeecahill.starsongs.songdata.dto.SongDto;
import com.mcgeecahill.starsongs.songdata.jpa.ArtistRepository;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ArtistRestControllerV1 {

    final static String API_VER = "v1";
    final static String  ENTITY_API_PATH = "/" + API_VER + "/artists";

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping(ENTITY_API_PATH)
    List<ArtistDto> all() {
        return artistRepository.findAll().stream().map(artist -> convertToDto(artist)).collect(Collectors.toUnmodifiableList());
    }

    @GetMapping(ENTITY_API_PATH + "/{id}")
    ArtistDto one(@PathVariable Integer id) {
        return convertToDto(artistRepository.findById(id).orElseThrow(() -> new ArtistNotFoundException(id)));
    }

    @PostMapping(ENTITY_API_PATH)
    ArtistDto newArtist(@RequestBody ArtistDto newArtist) {
        return convertToDto(artistRepository.save(convertToEntity(newArtist)));
    }

    @PutMapping(ENTITY_API_PATH + "/{id}")
    ArtistDto replaceArtist(@RequestBody final ArtistDto newArtist, @PathVariable final Integer id) {
        if (!id.equals(newArtist.getId())) {
            throw new InvalidEntityUpdateException("Artist ID from URL (" + id
                    + ") and body (" + newArtist.getId() + ") do not match.");
        }

        return artistRepository.findById(id)
                .map(artist -> {
                    artist.setName(newArtist.getName());
                    return convertToDto(artistRepository.save(artist));
                })
                .orElseGet(() -> {
                    return convertToDto(artistRepository.save(convertToEntity(newArtist)));
                });
    }

    @DeleteMapping(ENTITY_API_PATH + "/{id}")
    void deleteArtist(@PathVariable final Integer id) {
        artistRepository.deleteById(id);
    }


    private ArtistDto convertToDto(final Artist artist) {
        return modelMapper.map(artist, ArtistDto.class);
    }

    private Artist convertToEntity(final ArtistDto artistDto) throws MappingException {
        return modelMapper.map(artistDto, Artist.class);
    }
}
