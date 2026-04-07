package com.bjj_metrics_brasil.athlete.model.request;

import com.bjj_metrics_brasil.athlete.model.Enum.BeltEnum;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAthleteDataRequest {

    private String name;
    private String email;
    private BeltEnum belt;
    private LocalDate birthDate;
}
