package com.mcgeecahill.starsongs.songdata.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Artist")
public class ArtistNotFoundException extends RuntimeException {
    ArtistNotFoundException(Integer id) {
        super("Could not find artist " + id);
    }
}
