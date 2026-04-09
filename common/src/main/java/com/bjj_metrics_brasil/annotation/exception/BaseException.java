package com.bjj_metrics_brasil.annotation.exception;

import java.util.Collections;
import java.util.Map;
import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {

    private final HttpStatus status;
    private final Map<String, String> fields;

    protected BaseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.fields = Collections.emptyMap();
    }

    protected BaseException(
        String message,
        HttpStatus status,
        Map<String, String> fields
    ) {
        super(message);
        this.status = status;
        this.fields = fields;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Map<String, String> getFields() {
        return this.fields;
    }
}
