package com.mcgeecahill.starsongs.songdata.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Song")
public class SongNotFoundException extends RuntimeException {
    SongNotFoundException(Integer id) {
        super("Could not find song " + id);
    }
}
