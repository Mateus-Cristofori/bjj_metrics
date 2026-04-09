package com.bjj_metrics_brasil.training.controller;

import com.bjj_metrics_brasil.annotation.AthleteUserId;
import com.bjj_metrics_brasil.training.model.request.CreateTrainingRequest;
import com.bjj_metrics_brasil.training.model.response.AllUserTrainingsResponse;
import com.bjj_metrics_brasil.training.service.TrainingService;
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
@RequestMapping("/api/v1/training")
@AllArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;

    @GetMapping("/list-all")
    public List<AllUserTrainingsResponse> listAllUserTraining(
        @AthleteUserId UUID athleteId
    ) {
        return trainingService.listAllUserTrainings(athleteId);
    }

    @GetMapping("/retrieve/{trainingId}")
    public AllUserTrainingsResponse retrieveByTrainingId(@PathVariable UUID trainingId) {
        return trainingService.retrieveByTrainingId(trainingId);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTraining(
        @AthleteUserId UUID athleteId,
        @RequestBody @Valid CreateTrainingRequest createTrainingRequest
    ) {
        trainingService.createTraining(athleteId, createTrainingRequest);
    }
}
