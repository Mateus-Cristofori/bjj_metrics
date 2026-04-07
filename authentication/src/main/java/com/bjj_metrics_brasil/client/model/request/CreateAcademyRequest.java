package com.bjj_metrics_brasil.client.model.request;

import com.bjj_metrics_brasil.onboarding.model.Enum.CountryEnum;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateAcademyRequest {

    private UUID userId;
    private String academyName;
    private String city;
    private String state;
    private CountryEnum country;
}
