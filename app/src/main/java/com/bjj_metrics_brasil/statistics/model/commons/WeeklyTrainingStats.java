package com.bjj_metrics_brasil.statistics.model.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WeeklyTrainingStats {

    private String day;
    private Long value;
}
