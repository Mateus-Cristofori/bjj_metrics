package com.bjj_metrics_brasil.statistics.projection.service;

import com.bjj_metrics_brasil.fight.repository.FightRepository;
import com.bjj_metrics_brasil.statistics.model.commons.FightStats;
import com.bjj_metrics_brasil.statistics.projection.model.FightStatsProjection;
import com.bjj_metrics_brasil.utils.CalculatePercentage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FightStatsService {

    private final FightRepository fightRepository;
    private final CalculatePercentage calculatePercentage;

    public FightStats getFightStats(UUID athleteId) {
        FightStatsProjection stats = fightRepository.getFightStats(athleteId);

        long totalFights = getOrZero(stats.getTotalFights());
        long wins = getOrZero(stats.getWins());

        long losses = totalFights - wins;

        double winRate = calculatePercentage.calculatePercentage(wins, totalFights);

        Double avgDuration = fightRepository.getAverageDuration(athleteId);
        double averageDuration = avgDuration != null ? avgDuration : 0;

        return FightStats
            .builder()
            .totalFights(totalFights)
            .wins(wins)
            .losses(losses)
            .winRate(winRate)
            .averageDurationMinutes(round(averageDuration))
            .build();
    }

    private long getOrZero(Long value) {
        return value != null ? value : 0L;
    }

    private double round(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
