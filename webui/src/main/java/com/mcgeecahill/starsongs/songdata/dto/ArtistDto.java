package com.mcgeecahill.starsongs.songdata.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

// TODO replace with JAR from songdata
@JsonIgnoreProperties(ignoreUnknown = true)
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

    @Override
    public String toString() {
        return "ArtistDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ArtistDto artistDto = (ArtistDto) o;
        return Objects.equals(id, artistDto.id) && Objects.equals(name, artistDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
