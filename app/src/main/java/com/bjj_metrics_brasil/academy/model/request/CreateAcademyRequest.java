package com.bjj_metrics_brasil.academy.model.request;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAcademyRequest {

    private UUID userId;
    private String academyName;
    private String city;
    private String state;
    private String country;
}
