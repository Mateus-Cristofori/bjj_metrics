package com.bjj_metrics_brasil.athlete.service;

import com.bjj_metrics_brasil.athlete.model.request.CreateAthleteRequest;
import com.bjj_metrics_brasil.athlete.model.response.RetrieveAthleteResponse;
import java.util.UUID;

public interface AthleteService {
    void createAthlete(CreateAthleteRequest createAthleteRequest);
    RetrieveAthleteResponse retrieveAthleteByUserId(UUID userId);
    RetrieveAthleteResponse retrieveAthleteById(UUID athleteId);
}
