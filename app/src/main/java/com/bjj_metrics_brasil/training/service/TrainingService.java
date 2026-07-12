package com.bjj_metrics_brasil.training.service;

import com.bjj_metrics_brasil.training.model.request.CreateTrainingRequest;
import com.bjj_metrics_brasil.training.model.request.CreateTrainingWithRollsRequest;
import com.bjj_metrics_brasil.training.model.response.AllUserTrainingsResponse;
import java.util.List;
import java.util.UUID;

public interface TrainingService {
    UUID createTraining(UUID athleteId, CreateTrainingRequest createTrainingRequest);
    List<AllUserTrainingsResponse> listAllUserTrainings(UUID athleteId);
    AllUserTrainingsResponse retrieveByTrainingId(UUID trainingId);
    void createTrainingWithRolls(
        UUID athleteId,
        CreateTrainingWithRollsRequest createTrainingWithRollsRequest
    );
}
