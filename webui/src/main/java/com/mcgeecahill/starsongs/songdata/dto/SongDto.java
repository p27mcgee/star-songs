package com.mcgeecahill.starsongs.songdata.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.Objects;

// TODO replace with JAR from songdata
@JsonIgnoreProperties(ignoreUnknown = true)
public class SongDto {
    private Integer id;
    private String title;
    private Integer artistId;
    private Date releaseDate;
    private String url;
    private Double distance;

    public SongDto() {
    }

    public SongDto(final Integer id, final String title, final Integer artistId, final String url, final Double distance) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
        this.url = url;
        this.distance = distance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(final Integer artistId) {
        this.artistId = artistId;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(final Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(final Double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "SongDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artistId=" + artistId +
                ", releaseDate=" + releaseDate +
                ", url='" + url + '\'' +
                ", distance=" + distance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SongDto songDto = (SongDto) o;
        return Objects.equals(id, songDto.id) && Objects.equals(title, songDto.title) && Objects.equals(artistId, songDto.artistId) && Objects.equals(releaseDate, songDto.releaseDate) && Objects.equals(url, songDto.url) && Objects.equals(distance, songDto.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, artistId, releaseDate, url, distance);
    }
}
