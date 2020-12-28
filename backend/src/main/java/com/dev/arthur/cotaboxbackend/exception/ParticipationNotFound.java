package com.dev.arthur.cotaboxbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;


public class ParticipationNotFound extends HttpStatusCodeException {
    public ParticipationNotFound() {
        super(HttpStatus.BAD_REQUEST, "A participação não existe!");
    }
}
