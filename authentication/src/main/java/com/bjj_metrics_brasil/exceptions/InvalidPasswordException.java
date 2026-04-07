package com.bjj_metrics_brasil.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidPasswordException extends ResponseStatusException {

    public InvalidPasswordException() {
        super(HttpStatus.UNAUTHORIZED, "Invalid password!");
    }
}
