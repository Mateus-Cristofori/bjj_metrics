package com.bjj_metrics_brasil.statistics.controller;

import com.bjj_metrics_brasil.annotation.AthleteUserId;
import com.bjj_metrics_brasil.statistics.model.response.AthleteStatsResponse;
import com.bjj_metrics_brasil.statistics.model.response.DashboardResponse;
import com.bjj_metrics_brasil.statistics.service.StatisticsService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/statistics")
@AllArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/list/user-stats")
    public AthleteStatsResponse listUserStatistics(@AthleteUserId UUID athleteId) {
        return statisticsService.listUserStatistics(athleteId);
    }

    @GetMapping("/dashboard")
    public DashboardResponse dashboard(@AthleteUserId UUID athleteId) {
        return statisticsService.dashboard(athleteId);
    }
}
