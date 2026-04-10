package com.bjj_metrics_brasil.fight.controller;

import com.bjj_metrics_brasil.annotation.AthleteUserId;
import com.bjj_metrics_brasil.fight.model.request.CreateUserFightRequest;
import com.bjj_metrics_brasil.fight.model.response.ListAllUserFightsResponse;
import com.bjj_metrics_brasil.fight.service.FightService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fight")
@AllArgsConstructor
public class FightController {

    private final FightService fightService;

    @GetMapping("/list-all")
    public List<ListAllUserFightsResponse> listAllUserFight(
        @AthleteUserId UUID athleteId
    ) {
        return fightService.listAllUserFight(athleteId);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUserFight(
        @AthleteUserId UUID athleteId,
        @RequestBody @Valid CreateUserFightRequest createUserFightRequest
    ) {
        fightService.createUserFight(athleteId, createUserFightRequest);
    }
}
