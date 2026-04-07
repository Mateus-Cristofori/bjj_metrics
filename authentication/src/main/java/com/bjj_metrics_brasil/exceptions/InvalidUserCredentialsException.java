package com.bjj_metrics_brasil.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidUserCredentialsException extends ResponseStatusException {

    public InvalidUserCredentialsException() {
        super(HttpStatus.UNAUTHORIZED, "Invalid user credentials!");
    }
}
