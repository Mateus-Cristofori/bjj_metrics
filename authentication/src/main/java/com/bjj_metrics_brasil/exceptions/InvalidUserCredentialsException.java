package com.bjj_metrics_brasil.exceptions;

import com.bjj_metrics_brasil.annotation.exception.BaseException;
import java.util.Map;
import org.springframework.http.HttpStatus;

public class InvalidUserCredentialsException extends BaseException {

    public InvalidUserCredentialsException(Map<String, String> fields) {
        super("Invalid user credentials!", HttpStatus.UNAUTHORIZED, fields);
    }

    public InvalidUserCredentialsException() {
        super("Invalid user credentials!", HttpStatus.UNAUTHORIZED);
    }
}
