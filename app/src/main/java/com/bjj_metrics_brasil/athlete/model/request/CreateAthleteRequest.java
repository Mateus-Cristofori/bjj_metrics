package com.bjj_metrics_brasil.athlete.model.request;

import com.bjj_metrics_brasil.athlete.model.Enum.BeltEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAthleteRequest {

    private UUID userId;
    private String name;
    private String lastname;
    private String email;
    private BeltEnum belt;
    private Double weight;
    private String academyName;
    private String city;
    private LocalDate birthDate;
}