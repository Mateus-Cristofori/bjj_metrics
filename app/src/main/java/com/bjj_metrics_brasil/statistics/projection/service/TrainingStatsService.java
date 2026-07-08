package com.bjj_metrics_brasil.statistics.projection.service;

import com.bjj_metrics_brasil.statistics.model.commons.AthletePerformance;
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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
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
        LocalDate today = LocalDate.now();

        LocalDate startDate = today.with(
            TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)
        );
        LocalDate endDate = startDate.plusDays(6);

        Map<LocalDate, Long> trainings = trainingRepository
            .getWeeklyTrainings(athleteId, startDate, endDate)
            .stream()
            .collect(
                Collectors.toMap(
                    WeeklyTrainingProjection::getTrainingDate,
                    WeeklyTrainingProjection::getTotal
                )
            );

        List<WeeklyTrainingStats> result = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            LocalDate day = startDate.plusDays(i);

            result.add(
                new WeeklyTrainingStats(
                    convertDay.convert(day.getDayOfWeek()),
                    trainings.getOrDefault(day, 0L)
                )
            );
        }

        return result;
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

    public List<AthletePerformance> getAthletePerformance(UUID athleteId) {
        LocalDate today = LocalDate.now();

        LocalDate startDate = today.with(
            TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)
        );
        LocalDate endDate = startDate.plusDays(6);

        return trainingRepository
            .getAthleteTrainingPerformance(athleteId, startDate, endDate)
            .stream()
            .map(projection ->
                new AthletePerformance(
                    projection.getVeryBad(),
                    projection.getBad(),
                    projection.getAverage(),
                    projection.getGood(),
                    projection.getExcellent()
                )
            )
            .toList();
    }

    public Integer getTrainingStreak(UUID athleteId) {
        List<LocalDate> trainingDates = trainingRepository.findTrainingDates(athleteId);

        if (trainingDates.isEmpty()) {
            return 0;
        }

        LocalDate today = LocalDate.now();
        LocalDate lastTraining = trainingDates.get(0);

        if (lastTraining.isBefore(today.minusDays(1))) {
            return 0;
        }

        int streak = 1;

        for (int i = 0; i < trainingDates.size() - 1; i++) {
            LocalDate current = trainingDates.get(i);
            LocalDate previous = trainingDates.get(i + 1);

            if (current.minusDays(1).equals(previous)) {
                streak++;
            } else {
                break;
            }
        }
        return streak;
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
