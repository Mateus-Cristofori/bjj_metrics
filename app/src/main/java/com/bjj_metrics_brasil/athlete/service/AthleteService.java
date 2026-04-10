package com.bjj_metrics_brasil.athlete.service;

import com.bjj_metrics_brasil.athlete.model.request.CreateAthleteRequest;
import com.bjj_metrics_brasil.athlete.model.response.RetrieveAthleteByUserIdResponse;
import java.util.UUID;

public interface AthleteService {
    void createAthlete(CreateAthleteRequest createAthleteRequest);
    RetrieveAthleteByUserIdResponse retrieveAthleteByUserId(UUID userId);
}
