package com.bjj_metrics_brasil.roll.model.request;

import com.bjj_metrics_brasil.athlete.model.Enum.BeltEnum;
import com.bjj_metrics_brasil.roll.model.Enum.StartPositionEnum;
import com.bjj_metrics_brasil.training.model.Enum.TrainingIntensityEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRollRequest {

    @NotNull(message = "The training id must be provided")
    private UUID trainingId;

    @NotNull(message = "The duration of the roll must be provided")
    @Min(value = 1, message = "Duration must be at least 1 minute")
    private Integer durationMinutes;

    @NotNull(message = "The intensity must be provided")
    private TrainingIntensityEnum intensity;

    @NotNull(message = "The partner's name must be provided.")
    @NotBlank(message = "The partner's name cannot be blank")
    private String partnerName;

    @NotNull(message = "The partner's belt must be provided")
    private BeltEnum partnerBelt;

    private StartPositionEnum startPosition;
    private Integer submissionsApplied;
    private Integer submissionsSuffered;
    private Integer sweeps;
    private Integer passes;
    private String notes;
}
