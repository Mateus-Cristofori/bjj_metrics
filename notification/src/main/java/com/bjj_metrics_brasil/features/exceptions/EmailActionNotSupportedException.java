package com.bjj_metrics_brasil.features.exceptions;

import com.bjj_metrics_brasil.annotation.exception.BaseException;
import org.springframework.http.HttpStatus;

public class EmailActionNotSupportedException extends BaseException {

    public EmailActionNotSupportedException() {
        super("Email action not supported", HttpStatus.BAD_REQUEST);
    }
}
