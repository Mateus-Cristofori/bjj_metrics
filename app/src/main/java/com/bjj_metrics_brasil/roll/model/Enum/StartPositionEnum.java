package com.bjj_metrics_brasil.roll.model.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StartPositionEnum {
    STANDING,
    CLOSED_GUARD,
    OPEN_GUARD,
    HALF_GUARD,
    ON_KNEES,
    SIDE,
    MOUNT,
    BACK_CONTROL,
    KNEE_ON_BELLY;

    @JsonCreator
    public static StartPositionEnum fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return StartPositionEnum.valueOf(value);
    }
}
