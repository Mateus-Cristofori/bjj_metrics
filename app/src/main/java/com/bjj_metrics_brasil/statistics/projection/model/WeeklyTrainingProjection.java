package com.bjj_metrics_brasil.statistics.projection.model;

import java.time.LocalDate;

public interface WeeklyTrainingProjection {
    LocalDate getDate();
    Long getTotal();
}
