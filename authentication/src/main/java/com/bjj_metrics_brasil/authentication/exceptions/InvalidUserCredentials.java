package com.bjj_metrics_brasil.authentication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidUserCredentials extends ResponseStatusException {

    public InvalidUserCredentials() {
        super(HttpStatus.BAD_REQUEST, "Invalid user credentials");
    }
}
