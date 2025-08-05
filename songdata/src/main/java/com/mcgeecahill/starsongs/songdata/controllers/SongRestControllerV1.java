package com.mcgeecahill.starsongs.songdata.controllers;

import com.mcgeecahill.starsongs.songdata.dto.SongDto;
import com.mcgeecahill.starsongs.songdata.domain.Song;
import com.mcgeecahill.starsongs.songdata.jpa.ArtistRepository;
import com.mcgeecahill.starsongs.songdata.jpa.SongRepository;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
final public class SongRestControllerV1 {

    final static String API_VER = "v1";
    final static String  ENTITY_API_PATH = "/" + API_VER + "/songs";

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(ENTITY_API_PATH)
    List<SongDto> all() {
        return songRepository.findAll().stream().map(song -> convertToDto(song)).collect(Collectors.toUnmodifiableList());
    }

    @GetMapping(ENTITY_API_PATH + "/{id}")
    SongDto one(@PathVariable Integer id) {
        return convertToDto(songRepository.findById(id).orElseThrow(() -> new SongNotFoundException(id)));
    }

    @PostMapping(ENTITY_API_PATH)
    SongDto newSong(@RequestBody SongDto newSong) {
        return convertToDto(songRepository.save(convertToEntity(newSong)));
    }

    @PutMapping(ENTITY_API_PATH + "/{id}")
    SongDto replaceSong(@RequestBody final SongDto newSong, @PathVariable final Integer id) {
        if (!id.equals(newSong.getId())) {
            throw new InvalidEntityUpdateException("Song ID from URL (" + id
                    + ") and body (" + newSong.getId() + ") do not match.");
        }

        return songRepository.findById(id)
                .map(song -> {
                    song.setTitle(newSong.getTitle());
                    song.setArtist(
                            artistRepository.findById(newSong.getArtistId())
                                    .orElseThrow(() -> new ArtistNotFoundException(id)));
                    song.setUrl(newSong.getUrl());
                    song.setReleaseDate(newSong.getReleaseDate());
                    song.setDistance(newSong.getDistance());
                    return convertToDto(songRepository.save(song));
                })
                .orElseGet(() -> {
                    return convertToDto(songRepository.save(convertToEntity(newSong)));
                });
    }

    @DeleteMapping(ENTITY_API_PATH + "/{id}")
    void deleteSong(@PathVariable final Integer id) {
        songRepository.deleteById(id);
    }


    private SongDto convertToDto(final Song song) {
        return modelMapper.map(song, SongDto.class);
    }

    private Song convertToEntity(final SongDto songDto) throws MappingException {
        return  modelMapper.map(songDto, Song.class);
    }
}
