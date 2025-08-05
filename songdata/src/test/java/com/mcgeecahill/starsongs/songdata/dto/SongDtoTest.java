package com.mcgeecahill.starsongs.songdata.dto;

import com.mcgeecahill.starsongs.songdata.domain.Song;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SongDtoTest {
    private static ModelMapper modelMapper = new ModelMapper();

    @BeforeAll
    static void config() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Test
    public void songEntityToDtoTest() {
        Song song = new Song();
        // TODO
        song.setId(1);
        song.setTitle("foo");
        song.setUrl("www.test.com");

        SongDto songDto = modelMapper.map(song, SongDto.class);
        assertEquals(song.getId(), songDto.getId());
        assertEquals(song.getTitle(), songDto.getTitle());
        assertEquals(song.getUrl(), songDto.getUrl());
    }

    @Test
    public void songDtoToEntityTest() {
        final SongDto songDto = new SongDto();
        // TODO
        songDto.setId(1);
        songDto.setTitle("bar");
        songDto.setUrl("www.test.com");

        final Song song = modelMapper.map(songDto, Song.class);
        assertEquals(songDto.getId(), song.getId());
        assertEquals(songDto.getTitle(), song.getTitle());
        assertEquals(songDto.getUrl(), song.getUrl());
    }
}