package com.mcgeecahill.starsongs.songdata.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(length=64, nullable=false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "artistID")
    private Artist  artist;

    @Column(name="released")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @Column(name="URL", length=1024)
    private String url;


    @Column(name="distance", precision=8)
    private Double distance;

    public Song() {
    }

    public Song(final Integer id, final String title, final Artist artist, final String url, final Double distance) {
        this.id = id;
        this.title = title;
        this.artist = artist;
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
        return artist != null ? artist.getId() : null;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(final Artist artist) {
        this.artist = artist;
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
