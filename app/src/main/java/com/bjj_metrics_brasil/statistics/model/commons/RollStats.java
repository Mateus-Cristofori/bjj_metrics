package com.bjj_metrics_brasil.statistics.model.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RollStats {

    private long totalRolls;
    private double rollsPerTraining;
    private long submissionsApplied;
    private long submissionsSuffered;
    private long netSubmissions;
    private double submissionRate;
    private long sweeps;
    private long passes;
    private double sweepsPerRoll;
    private double passesPerRoll;
    private double averageIntensity;
}
