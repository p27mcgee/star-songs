package com.mcgeecahill.starsongs.songdata.dto;

public class ArtistDto {
   private Integer id;
    private String name;

    public ArtistDto() {
    }

    public ArtistDto(final String name, final Integer id) {
        this.name = name;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
