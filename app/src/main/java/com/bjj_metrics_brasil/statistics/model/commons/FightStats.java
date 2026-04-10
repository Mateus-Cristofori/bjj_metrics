package com.bjj_metrics_brasil.statistics.model.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FightStats {

    private long totalFights;
    private long wins;
    private long losses;
    private double winRate;
    private double averageDurationMinutes;
}
