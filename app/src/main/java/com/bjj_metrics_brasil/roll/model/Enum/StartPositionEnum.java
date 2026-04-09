package com.bjj_metrics_brasil.roll.model.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StartPositionEnum {
    CLOSED_GUARD,
    HALF_GUARD,
    STANDING;

    @JsonCreator
    public static StartPositionEnum fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return StartPositionEnum.valueOf(value);
    }
}
