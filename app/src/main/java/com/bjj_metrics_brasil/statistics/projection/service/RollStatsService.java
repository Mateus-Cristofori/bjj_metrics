package com.bjj_metrics_brasil.statistics.projection.service;

import com.bjj_metrics_brasil.roll.repository.RollRepository;
import com.bjj_metrics_brasil.statistics.model.commons.RollStats;
import com.bjj_metrics_brasil.statistics.projection.model.GameStatsProjection;
import com.bjj_metrics_brasil.statistics.projection.model.SubmissionStatsProjection;
import com.bjj_metrics_brasil.training.repository.TrainingRepository;
import com.bjj_metrics_brasil.utils.CalculatePercentage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RollStatsService {

    private final CalculatePercentage calculatePercentage;
    private final RollRepository rollRepository;
    private final TrainingRepository trainingRepository;

    public RollStats getRollStatics(UUID athleteId) {
        long totalRolls = rollRepository.countRolls(athleteId);
        long totalTrainings = trainingRepository.countByAthleteId(athleteId);

        double rollsPerTraining = safeDivide(totalRolls, totalTrainings);

        SubmissionStatsProjection submissionStats = rollRepository.getSubmissionStats(
            athleteId
        );

        long submissionsApplied = getOrZero(submissionStats.getSubmissionsApplied());
        long submissionsSuffered = getOrZero(submissionStats.getSubmissionsSuffered());

        long netSubmissions = submissionsApplied - submissionsSuffered;

        double submissionRate = calculatePercentage.calculatePercentage(
            submissionsApplied,
            totalRolls
        );

        GameStatsProjection gameStats = rollRepository.getGameStats(athleteId);

        long sweeps = getOrZero(gameStats.getSweeps());
        long passes = getOrZero(gameStats.getPasses());

        double sweepsPerRoll = safeDivide(sweeps, totalRolls);
        double passesPerRoll = safeDivide(passes, totalRolls);

        Double avgIntensity = rollRepository.getAverageIntensity(athleteId);
        double averageIntensity = round(avgIntensity != null ? avgIntensity : 0);

        return RollStats
            .builder()
            .totalRolls(totalRolls)
            .rollsPerTraining(round(rollsPerTraining))
            .submissionsApplied(submissionsApplied)
            .submissionsSuffered(submissionsSuffered)
            .netSubmissions(netSubmissions)
            .submissionRate(submissionRate)
            .sweeps(sweeps)
            .passes(passes)
            .sweepsPerRoll(round(sweepsPerRoll))
            .passesPerRoll(round(passesPerRoll))
            .averageIntensity(round(averageIntensity))
            .build();
    }

    private long getOrZero(Long value) {
        return value != null ? value : 0L;
    }

    private double safeDivide(long value, long total) {
        if (total == 0) {
            return 0;
        }
        return (double) value / total;
    }

    private double round(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
