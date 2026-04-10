package com.bjj_metrics_brasil.statistics.service.impl;

import com.bjj_metrics_brasil.statistics.model.commons.FightStats;
import com.bjj_metrics_brasil.statistics.model.commons.RollStats;
import com.bjj_metrics_brasil.statistics.model.commons.TrainingStats;
import com.bjj_metrics_brasil.statistics.model.response.AthleteStatsResponse;
import com.bjj_metrics_brasil.statistics.projection.service.FightStatsService;
import com.bjj_metrics_brasil.statistics.projection.service.RollStatsService;
import com.bjj_metrics_brasil.statistics.projection.service.TrainingStatsService;
import com.bjj_metrics_brasil.statistics.service.StatisticsService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class StatisticsServiceImpl implements StatisticsService {

    private final TrainingStatsService trainingStatsService;
    private final RollStatsService rollStatsService;
    private final FightStatsService fightStatsService;

    @Override
    public AthleteStatsResponse listUserStatistics(UUID athleteId) {
        log.info("Retrieving user statistics for athlete_id: {}", athleteId);
        TrainingStats trainingStats = trainingStatsService.getTrainingStats(athleteId);
        RollStats rollStats = rollStatsService.getRollStatics(athleteId);
        FightStats fightStats = fightStatsService.getFightStats(athleteId);

        return AthleteStatsResponse
            .builder()
            .training(trainingStats)
            .roll(rollStats)
            .fight(fightStats)
            .build();
    }
}
