package com.bjj_metrics_brasil.training.controller;

import com.bjj_metrics_brasil.annotation.AthleteUserId;
import com.bjj_metrics_brasil.training.model.request.CreateTrainingRequest;
import com.bjj_metrics_brasil.training.service.TrainingService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTraining(
        @AthleteUserId UUID userId,
        @RequestBody @Valid CreateTrainingRequest createTrainingRequest
    ) {
        trainingService.createTraining(userId, createTrainingRequest);
    }
}
