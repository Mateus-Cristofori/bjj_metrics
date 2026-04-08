package com.bjj_metrics_brasil.training.model.Enum;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TrainingIntensityEnum {
    LOW_INTENSITY(1),
    MEDIUM_INTENSITY(2),
    HIGH_INTENSITY(3);

    @JsonValue
    private final Integer value;
}
