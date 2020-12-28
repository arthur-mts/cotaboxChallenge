package com.dev.arthur.cotaboxbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;


public class ParticipationOutOfBound extends HttpStatusCodeException {
    public ParticipationOutOfBound(Float excess) {
        super(HttpStatus.BAD_REQUEST,"A soma das porcentagens excede em "+ excess +" o limite de 100%.");
    }
}
