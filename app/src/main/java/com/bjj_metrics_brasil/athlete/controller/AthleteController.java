package com.bjj_metrics_brasil.athlete.controller;

import com.bjj_metrics_brasil.athlete.model.request.CreateAthleteRequest;
import com.bjj_metrics_brasil.athlete.model.response.RetrieveAthleteByUserIdResponse;
import com.bjj_metrics_brasil.athlete.service.AthleteService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/athlete")
@AllArgsConstructor
public class AthleteController {

    private final AthleteService athleteService;

    @GetMapping("/retrieve/by-user-id/{userId}")
    public RetrieveAthleteByUserIdResponse retrieveAthleteByUserId(
        @PathVariable UUID userId
    ) {
        return athleteService.retrieveAthleteByUserId(userId);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAthlete(
        @RequestBody @Valid CreateAthleteRequest createAthleteRequest
    ) {
        athleteService.createAthlete(createAthleteRequest);
    }
}
