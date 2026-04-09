package com.bjj_metrics_brasil.exceptions;

import com.bjj_metrics_brasil.annotation.exception.BaseException;
import java.util.Map;
import org.springframework.http.HttpStatus;

public class TrainingNotFoundException extends BaseException {

    public TrainingNotFoundException() {
        super("Training not found!", HttpStatus.NOT_FOUND);
    }

    public TrainingNotFoundException(Map<String, String> fields) {
        super("Training not found!", HttpStatus.NOT_FOUND, fields);
    }
}
