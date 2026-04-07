package com.bjj_metrics_brasil.onboarding.model.Enum;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BeltEnum {
    WHITE("white"),
    BLUE("blue"),
    PURPLE("purple"),
    BROWN("brown"),
    BLACK("black");

    @JsonValue
    private final String value;
}
