package com.bjj_metrics_brasil.statistics.service;

import com.bjj_metrics_brasil.statistics.model.response.AthleteStatsResponse;
import com.bjj_metrics_brasil.statistics.model.response.DashboardResponse;
import java.util.UUID;

public interface StatisticsService {
    AthleteStatsResponse listUserStatistics(UUID athleteId);
    DashboardResponse dashboard(UUID athleteId);
}
