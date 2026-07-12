package com.bjj_metrics_brasil.training.model.request;

import com.bjj_metrics_brasil.roll.model.request.CreateRollRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTrainingWithRollsRequest {

    private CreateTrainingRequest trainingRequest;
    private CreateRollRequest rollRequest;
}
