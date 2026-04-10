package com.bjj_metrics_brasil.statistics.model.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingStats {

    private long totalTrainings;
    private double averageDurationMinutes;
    private double giPercentage;
    private double noGiPercentage;
    private double trainingsPerWeek;
}
