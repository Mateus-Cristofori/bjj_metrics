package com.bjj_metrics_brasil.athlete.service.impl;

import com.bjj_metrics_brasil.athlete.model.request.CreateAthleteRequest;
import com.bjj_metrics_brasil.athlete.model.request.UpdateAthleteDataRequest;
import com.bjj_metrics_brasil.athlete.repository.AthleteRepository;
import com.bjj_metrics_brasil.athlete.repository.entity.Athlete;
import com.bjj_metrics_brasil.athlete.service.AthleteService;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AthleteServiceImpl implements AthleteService {

    private final AthleteRepository athleteRepository;

    @Override
    public void createAthlete(CreateAthleteRequest createAthleteRequest) {
        athleteRepository.save(
            Athlete
                .builder()
                .athleteName(createAthleteRequest.getName())
                .belt(createAthleteRequest.getBelt())
                .weight(createAthleteRequest.getWeight())
                .birthDate(createAthleteRequest.getBirthDate())
                .createdAt(LocalDateTime.now())
                .build()
        );
    }

    @Override
    public void updateAthleteData(
        UUID userId,
        UpdateAthleteDataRequest updateAthleteDataRequest
    ) {}
}
