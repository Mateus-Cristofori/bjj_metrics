package com.bjj_metrics_brasil.training.service;

import com.bjj_metrics_brasil.training.model.request.CreateTrainingRequest;
import java.util.UUID;

public interface TrainingService {
    void createTraining(UUID userId, CreateTrainingRequest createTrainingRequest);
}
