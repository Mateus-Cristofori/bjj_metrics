package com.bjj_metrics_brasil.utils.responseBuilder;

import com.bjj_metrics_brasil.roll.model.response.AllUserTrainingsAndRollsResponse;
import com.bjj_metrics_brasil.roll.repository.entity.Roll;
import com.bjj_metrics_brasil.training.repository.entity.Training;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AllUserTrainingAndRollsResponseBuilder {

    public AllUserTrainingsAndRollsResponse build(Training training, List<Roll> rolls) {
        AllUserTrainingsAndRollsResponse.Training trainingResponse =
            AllUserTrainingsAndRollsResponse.Training
                .builder()
                .trainingId(training.getId())
                .trainingType(training.getTrainingType().name())
                .durationMinutes(training.getDurationMinutes())
                .intensity(training.getIntensity().name())
                .gi(training.getGi())
                .academyId(training.getAcademyId())
                .notes(training.getNotes())
                .trainingDate(training.getTrainingDate())
                .rolls(buildRollsTraining(rolls))
                .build();

        return AllUserTrainingsAndRollsResponse
            .builder()
            .training(trainingResponse)
            .build();
    }

    private List<AllUserTrainingsAndRollsResponse.RollsByTraining> buildRollsTraining(
        List<Roll> rolls
    ) {
        return rolls
            .stream()
            .map(roll ->
                AllUserTrainingsAndRollsResponse.RollsByTraining
                    .builder()
                    .intensity(roll.getIntensity().name())
                    .durationMinutes(roll.getDurationMinutes())
                    .partnerName(roll.getPartnerName())
                    .partnerBelt(roll.getPartnerBelt())
                    .startPosition(roll.getStartPosition())
                    .submissionsApplied(roll.getSubmissionsApplied())
                    .submissionsSuffered(roll.getSubmissionsSuffered())
                    .sweeps(roll.getSweeps())
                    .passes(roll.getPasses())
                    .notes(roll.getNotes())
                    .build()
            )
            .toList();
    }
}
