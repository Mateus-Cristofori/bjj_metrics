package com.bjj_metrics_brasil.exceptions;

import com.bjj_metrics_brasil.annotation.exception.BaseException;
import org.springframework.http.HttpStatus;
import java.util.Map;

public class InvalidPasswordException extends BaseException {

    public InvalidPasswordException(Map<String, String> fields) {
        super("Invalid password!", HttpStatus.UNAUTHORIZED, fields);
    }

    public InvalidPasswordException() {
        super("Invalid password!", HttpStatus.UNAUTHORIZED);
    }
}
