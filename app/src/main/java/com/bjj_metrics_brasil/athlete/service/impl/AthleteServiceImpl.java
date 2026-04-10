package com.bjj_metrics_brasil.athlete.service.impl;

import com.bjj_metrics_brasil.athlete.model.request.CreateAthleteRequest;
import com.bjj_metrics_brasil.athlete.model.response.RetrieveAthleteByUserIdResponse;
import com.bjj_metrics_brasil.athlete.repository.AthleteRepository;
import com.bjj_metrics_brasil.athlete.repository.entity.Athlete;
import com.bjj_metrics_brasil.athlete.service.AthleteService;
import com.bjj_metrics_brasil.exceptions.AthleteNotFoundException;
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
        log.info(
            "Registering new athlete for userId: {} ",
            createAthleteRequest.getUserId()
        );
        athleteRepository.save(
            Athlete
                .builder()
                .userId(createAthleteRequest.getUserId())
                .athleteName(
                    String.format(
                        "%s %s",
                        createAthleteRequest.getName(),
                        createAthleteRequest.getLastname()
                    )
                )
                .belt(createAthleteRequest.getBelt())
                .weight(createAthleteRequest.getWeight())
                .birthDate(createAthleteRequest.getBirthDate())
                .createdAt(LocalDateTime.now())
                .build()
        );
    }

    @Override
    public RetrieveAthleteByUserIdResponse retrieveAthleteByUserId(UUID userId) {
        Athlete athlete = athleteRepository
            .findByUserId(userId)
            .orElseThrow(AthleteNotFoundException::new);

        return RetrieveAthleteByUserIdResponse
            .builder()
            .id(athlete.getId())
            .athleteName(athlete.getAthleteName())
            .belt(athlete.getBelt())
            .weight(athlete.getWeight())
            .birthDate(athlete.getBirthDate())
            .build();
    }
}
