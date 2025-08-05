package com.mcgeecahill.starsongs.songdata.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="Data integrity violation")
public class InvalidEntityUpdateException extends RuntimeException {
    InvalidEntityUpdateException(final String failedConstraint) {
        super(failedConstraint);
    }
}
