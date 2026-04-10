package com.bjj_metrics_brasil.client.model.response;

import com.bjj_metrics_brasil.client.model.Enum.BeltEnum;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RetrieveAthleteByUserIdResponse {

    private UUID id;
    private String athleteName;
    private BeltEnum belt;
    private Double weight;
    private LocalDate birthDate;
}
