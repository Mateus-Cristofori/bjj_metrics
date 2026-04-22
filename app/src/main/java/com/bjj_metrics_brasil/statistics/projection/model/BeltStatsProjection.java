package com.bjj_metrics_brasil.statistics.projection.model;

import com.bjj_metrics_brasil.athlete.model.Enum.BeltEnum;

public interface BeltStatsProjection {
    BeltEnum getBelt();
    Long getTotal();
}
