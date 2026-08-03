package com.bjj_metrics_brasil.exceptions;

import com.bjj_metrics_brasil.annotation.exception.BaseException;
import org.springframework.http.HttpStatus;

public class OtpMaxAttemptsException extends BaseException {

    public OtpMaxAttemptsException() {
        super(
            "Número máximo de tentativas excedido. Gere um novo código.",
            HttpStatus.TOO_MANY_REQUESTS
        );
    }
}
