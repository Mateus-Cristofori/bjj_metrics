package com.bjj_metrics_brasil.training.model.response;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllUserTrainingsResponse {

    private UUID trainingId;
    private String trainingType;
    private Integer durationMinutes;
    private String intensity;
    private Boolean gi;
    private UUID academyId;
    private String notes;
    private LocalDate trainingDate;
}
