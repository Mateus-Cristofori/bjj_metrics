package com.bjj_metrics_brasil.roll.model.response;

import com.bjj_metrics_brasil.athlete.model.Enum.BeltEnum;
import com.bjj_metrics_brasil.roll.model.Enum.StartPositionEnum;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllUserTrainingsAndRollsResponse {

    private Training training;

    @Data
    @Builder
    public static class Training {

        private UUID trainingId;
        private String trainingType;
        private Integer durationMinutes;
        private String intensity;
        private Boolean gi;
        private UUID academyId;
        private String notes;
        private LocalDate trainingDate;
        private List<RollsByTraining> rolls;
    }

    @Data
    @Builder
    public static class RollsByTraining {

        private String intensity;
        private Integer durationMinutes;
        private String partnerName;
        private BeltEnum partnerBelt;
        private StartPositionEnum startPosition;
        private Integer submissionsApplied;
        private Integer submissionsSuffered;
        private Integer sweeps;
        private Integer passes;
        private String notes;
    }
}
