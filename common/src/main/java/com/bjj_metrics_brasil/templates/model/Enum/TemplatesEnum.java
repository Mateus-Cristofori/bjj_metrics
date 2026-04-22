package com.bjj_metrics_brasil.templates.model.Enum;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TemplatesEnum {
    FORGOT_PASSWORD("email/ForgotPasswordTemplate");

    @JsonValue
    private final String value;
}
