package com.bjj_metrics_brasil.statistics.model.response;

import com.bjj_metrics_brasil.statistics.model.commons.BeltStats;
import com.bjj_metrics_brasil.statistics.model.commons.TechniqueStats;
import com.bjj_metrics_brasil.statistics.model.commons.TrainingSequenceStats;
import com.bjj_metrics_brasil.statistics.model.commons.WeeklyTrainingStats;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {

    private List<WeeklyTrainingStats> weeklyTrainings;
    private List<TrainingSequenceStats> trainingSequence;
    private List<TechniqueStats> topTechniques;
    private List<BeltStats> beltStats;
}
