package com.bjj_metrics_brasil.statistics.model.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AthletePerformance {

    private Integer veryBad;
    private Integer bad;
    private Integer average;
    private Integer good;
    private Integer excellent;
}
