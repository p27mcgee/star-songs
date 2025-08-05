package com.mcgeecahill.starsongs.songdata.dto;

import com.mcgeecahill.starsongs.songdata.domain.Artist;

import java.util.Date;

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
}
