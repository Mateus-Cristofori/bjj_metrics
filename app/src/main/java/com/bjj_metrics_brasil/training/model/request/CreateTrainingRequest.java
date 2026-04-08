package com.bjj_metrics_brasil.training.model.request;

import com.bjj_metrics_brasil.training.model.Enum.TrainingIntensityEnum;
import com.bjj_metrics_brasil.training.model.Enum.TrainingTypeEnum;
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

    private UUID academyId;

    @NotNull(message = "Gi indicator must be provided (true for gi, false for no-gi)")
    private Boolean gi;

    @NotNull(message = "Number of rounds must be provided")
    private Integer rounds;

    private String notes;
}
