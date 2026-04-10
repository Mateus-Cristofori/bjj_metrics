package com.bjj_metrics_brasil.statistics.projection.service;

import com.bjj_metrics_brasil.statistics.model.commons.TrainingStats;
import com.bjj_metrics_brasil.statistics.projection.model.GiStatsProjection;
import com.bjj_metrics_brasil.statistics.projection.model.TrainingStatsProjection;
import com.bjj_metrics_brasil.training.repository.TrainingRepository;
import com.bjj_metrics_brasil.utils.CalculatePercentage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainingStatsService {

    private final TrainingRepository trainingRepository;
    private final CalculatePercentage calculatePercentage;

    public TrainingStats getTrainingStats(UUID athleteId) {
        TrainingStatsProjection stats = trainingRepository.getTrainingStats(athleteId);

        long totalTrainings = getOrZero(stats.getTotalTrainings());
        double avgDuration = getOrZero(stats.getAverageDuration());

        GiStatsProjection giStats = trainingRepository.getGiStats(athleteId);

        long giCount = getOrZero(giStats.getGiCount());
        long noGiCount = getOrZero(giStats.getNoGiCount());

        double giPercentage = calculatePercentage.calculatePercentage(
            giCount,
            totalTrainings
        );
        double noGiPercentage = calculatePercentage.calculatePercentage(
            noGiCount,
            totalTrainings
        );

        return TrainingStats
            .builder()
            .totalTrainings(totalTrainings)
            .averageDurationMinutes(round(avgDuration))
            .giPercentage(giPercentage)
            .noGiPercentage(noGiPercentage)
            .build();
    }

    private long getOrZero(Long value) {
        return value != null ? value : 0L;
    }

    private double getOrZero(Double value) {
        return value != null ? value : 0.0;
    }

    private double round(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
