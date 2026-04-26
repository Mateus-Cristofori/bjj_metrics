package com.bjj_metrics_brasil.statistics.projection.service;

import com.bjj_metrics_brasil.statistics.model.commons.TrainingSequenceStats;
import com.bjj_metrics_brasil.statistics.model.commons.TrainingStats;
import com.bjj_metrics_brasil.statistics.model.commons.WeeklyTrainingStats;
import com.bjj_metrics_brasil.statistics.projection.model.GiStatsProjection;
import com.bjj_metrics_brasil.statistics.projection.model.TrainingStatsProjection;
import com.bjj_metrics_brasil.statistics.projection.model.WeeklyTrainingProjection;
import com.bjj_metrics_brasil.training.repository.TrainingRepository;
import com.bjj_metrics_brasil.utils.CalculatePercentage;
import com.bjj_metrics_brasil.utils.ConvertDay;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainingStatsService {

    private final TrainingRepository trainingRepository;
    private final CalculatePercentage calculatePercentage;
    private final ConvertDay convertDay;

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

    public List<WeeklyTrainingStats> getWeeklyTrainings(UUID athleteId) {
        LocalDate startDate = LocalDate.now().minusDays(6);

        List<WeeklyTrainingProjection> weeklyTrainings =
            trainingRepository.getWeeklyTrainings(athleteId, startDate);

        return weeklyTrainings
            .stream()
            .map(weeklyTrainingProjection ->
                new WeeklyTrainingStats(
                    convertDay.convert(weeklyTrainingProjection.getDayOfWeek()),
                    weeklyTrainingProjection.getTotal()
                )
            )
            .toList();
    }

    public List<TrainingSequenceStats> getTrainingSequence(UUID athleteId) {
        return trainingRepository
            .getLastWeeks(athleteId)
            .stream()
            .map(trainingProjection ->
                new TrainingSequenceStats(
                    trainingProjection.getWeek(),
                    trainingProjection.getTotal()
                )
            )
            .toList();
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
