package com.mcgeecahill.starsongs.songdata.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(length=64, nullable=false)
    private String name;

    @OneToMany(mappedBy = "artist")
    private Set<Song> events;

    public Artist() {
        this.name = name;
        this.id = id;
    }

    public Artist(final String name, final Integer id) {
        this.name = name;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
