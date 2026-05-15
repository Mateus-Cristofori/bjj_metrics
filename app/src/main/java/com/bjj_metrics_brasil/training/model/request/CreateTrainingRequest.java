package com.bjj_metrics_brasil.training.model.request;

import com.bjj_metrics_brasil.training.model.Enum.AthleteTrainingPerformanceEnum;
import com.bjj_metrics_brasil.training.model.Enum.TrainingIntensityEnum;
import com.bjj_metrics_brasil.training.model.Enum.TrainingTypeEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTrainingRequest {

    @NotNull(message = "Training date must be provided")
    private LocalDate trainingDate;

    @NotNull(message = "Training type must be provided")
    private TrainingTypeEnum trainingType;

    @NotNull(message = "Training duration (in minutes) must be provided")
    private Integer durationMinutes;

    @NotNull(message = "Training intensity must be provided")
    private TrainingIntensityEnum intensity;

    @NotNull(message = "Your performance in training needs to be reported.")
    private AthleteTrainingPerformanceEnum athletePerformance;

    private UUID academyId;

    @NotNull(message = "Gi indicator must be provided")
    private Boolean gi;

    private String notes;
}
