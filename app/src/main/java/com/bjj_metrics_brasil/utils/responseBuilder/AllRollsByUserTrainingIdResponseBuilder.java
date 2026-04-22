package com.bjj_metrics_brasil.utils.responseBuilder;

import com.bjj_metrics_brasil.roll.model.response.AllRollsByUserTrainingIdResponse;
import com.bjj_metrics_brasil.roll.repository.entity.Roll;
import com.bjj_metrics_brasil.training.repository.entity.Training;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AllRollsByUserTrainingIdResponseBuilder {

    public AllRollsByUserTrainingIdResponse builder(Training training, List<Roll> rolls) {
        return AllRollsByUserTrainingIdResponse
            .builder()
            .trainingId(training.getId())
            .durationMinutes(training.getDurationMinutes())
            .intensity(training.getIntensity())
            .gi(training.getGi())
            .academyId(training.getAcademyId())
            .notes(training.getNotes())
            .trainingDate(training.getTrainingDate())
            .rolls(builderRolls(rolls))
            .build();
    }

    private List<AllRollsByUserTrainingIdResponse.Rolls> builderRolls(List<Roll> rolls) {
        return rolls
            .stream()
            .map(roll ->
                AllRollsByUserTrainingIdResponse.Rolls
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
