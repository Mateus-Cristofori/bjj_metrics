package com.bjj_metrics_brasil.exceptions;

import com.bjj_metrics_brasil.annotation.exception.BaseException;
import org.springframework.http.HttpStatus;

public class AthleteNotFoundException extends BaseException {

    public AthleteNotFoundException() {
        super("Athlete not found!", HttpStatus.NOT_FOUND);
    }
}
