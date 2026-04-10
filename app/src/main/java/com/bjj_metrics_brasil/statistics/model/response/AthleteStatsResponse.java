package com.bjj_metrics_brasil.statistics.model.response;

import com.bjj_metrics_brasil.statistics.model.commons.FightStats;
import com.bjj_metrics_brasil.statistics.model.commons.RollStats;
import com.bjj_metrics_brasil.statistics.model.commons.TrainingStats;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AthleteStatsResponse {

    private TrainingStats training;
    private RollStats roll;
    private FightStats fight;
}
