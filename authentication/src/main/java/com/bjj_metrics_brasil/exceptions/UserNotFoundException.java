package com.bjj_metrics_brasil.exceptions;

import com.bjj_metrics_brasil.annotation.exception.BaseException;
import org.springframework.http.HttpStatus;
import java.util.Map;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException(Map<String, String> fields) {
        super("User not found", HttpStatus.NOT_FOUND, fields);
    }

    public UserNotFoundException() {
        super("User not found", HttpStatus.NOT_FOUND);
    }
}
