package com.bjj_metrics_brasil.statistics.service;

import com.bjj_metrics_brasil.statistics.model.response.AthleteStatsResponse;
import java.util.UUID;

public interface StatisticsService {
    AthleteStatsResponse listUserStatistics(UUID athleteId);
}
