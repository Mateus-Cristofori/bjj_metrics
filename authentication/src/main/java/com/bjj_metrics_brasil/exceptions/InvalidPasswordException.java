package com.bjj_metrics_brasil.exceptions;

import com.bjj_metrics_brasil.annotation.exception.BaseException;
import java.util.Map;
import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends BaseException {

    public InvalidPasswordException(Map<String, String> fields) {
        super("Invalid password!", HttpStatus.UNAUTHORIZED, fields);
    }

    public InvalidPasswordException() {
        super("Invalid password!", HttpStatus.UNAUTHORIZED);
    }
}
