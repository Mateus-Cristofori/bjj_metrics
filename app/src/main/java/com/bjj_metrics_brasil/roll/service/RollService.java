package com.bjj_metrics_brasil.roll.service;

import com.bjj_metrics_brasil.roll.model.request.CreateRollRequest;
import com.bjj_metrics_brasil.roll.model.response.AllRollsByUserTrainingIdResponse;
import com.bjj_metrics_brasil.roll.model.response.AllUserTrainingsAndRollsResponse;
import java.util.List;
import java.util.UUID;

public interface RollService {
    void createRoll(CreateRollRequest createRollRequest);
    List<AllUserTrainingsAndRollsResponse> listAllUserRolls(UUID athleteId);
    AllRollsByUserTrainingIdResponse listAllUserRollsByTrainingId(
        UUID trainingId,
        UUID athleteId
    );
}
