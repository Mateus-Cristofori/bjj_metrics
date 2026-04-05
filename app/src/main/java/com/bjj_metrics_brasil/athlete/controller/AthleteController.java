package com.bjj_metrics_brasil.athlete.controller;

import com.bjj_metrics_brasil.athlete.model.request.CreateAthleteRequest;
import com.bjj_metrics_brasil.athlete.model.request.UpdateAthleteDataRequest;
import com.bjj_metrics_brasil.athlete.service.AthleteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController()
@RequestMapping("/api/v1/athlete")
@AllArgsConstructor
public class AthleteController {

    private final AthleteService athleteService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAthlete(
        @RequestBody @Valid CreateAthleteRequest createAthleteRequest
    ) {
        athleteService.createAthlete(createAthleteRequest);
    }

    // ! Endpoint privado
    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAthlete(
            // ! Anotação de pegar o ID do usuário via token
    ) {}

    // ! Endpoint privado
    @PatchMapping("/update")
    public void updateAthleteData(
        // ! Anotação de pegar o ID via token
        UUID userId,
        @RequestBody @Valid UpdateAthleteDataRequest updateAthleteDataRequest
    ) {
        athleteService.updateAthleteData(userId, updateAthleteDataRequest);
    }
}