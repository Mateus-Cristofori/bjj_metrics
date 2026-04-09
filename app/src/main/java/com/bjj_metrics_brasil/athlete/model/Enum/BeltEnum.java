package com.bjj_metrics_brasil.athlete.model.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
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

    @JsonCreator
    public static BeltEnum fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return BeltEnum.valueOf(value);
    }
}
