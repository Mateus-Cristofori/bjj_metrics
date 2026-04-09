package com.bjj_metrics_brasil.roll.controller;

import com.bjj_metrics_brasil.annotation.AthleteUserId;
import com.bjj_metrics_brasil.roll.model.request.CreateRollRequest;
import com.bjj_metrics_brasil.roll.model.response.AllRollsByUserTrainingIdResponse;
import com.bjj_metrics_brasil.roll.model.response.AllUserTrainingsAndRollsResponse;
import com.bjj_metrics_brasil.roll.service.RollService;
import jakarta.validation.Valid;
import java.util.List;
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
@RequestMapping("/api/v1/roll")
@AllArgsConstructor
public class RollController {

    private final RollService rollService;

    @GetMapping("/list-all")
    public List<AllUserTrainingsAndRollsResponse> listAllUserRolls(
        @AthleteUserId UUID athleteId
    ) {
        return rollService.listAllUserRolls(athleteId);
    }

    @GetMapping("/list-all/{trainingId}")
    public AllRollsByUserTrainingIdResponse listAllUserRollsByTrainingId(
        @PathVariable UUID trainingId,
        @AthleteUserId UUID athleteId
    ) {
        return rollService.listAllUserRollsByTrainingId(trainingId, athleteId);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRoll(@RequestBody @Valid CreateRollRequest createRollRequest) {
        rollService.createRoll(createRollRequest);
    }
}
