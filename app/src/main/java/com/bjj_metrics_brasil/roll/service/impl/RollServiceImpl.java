package com.bjj_metrics_brasil.roll.service.impl;

import com.bjj_metrics_brasil.exceptions.TrainingNotFoundException;
import com.bjj_metrics_brasil.roll.model.Enum.StartPositionEnum;
import com.bjj_metrics_brasil.roll.model.request.CreateRollRequest;
import com.bjj_metrics_brasil.roll.model.response.AllRollsByUserTrainingIdResponse;
import com.bjj_metrics_brasil.roll.model.response.AllUserTrainingsAndRollsResponse;
import com.bjj_metrics_brasil.roll.repository.RollRepository;
import com.bjj_metrics_brasil.roll.repository.entity.Roll;
import com.bjj_metrics_brasil.roll.service.RollService;
import com.bjj_metrics_brasil.training.repository.TrainingRepository;
import com.bjj_metrics_brasil.training.repository.entity.Training;
import com.bjj_metrics_brasil.utils.responseBuilder.AllRollsByUserTrainingIdResponseBuilder;
import com.bjj_metrics_brasil.utils.responseBuilder.AllUserTrainingAndRollsResponseBuilder;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RollServiceImpl implements RollService {

    private final RollRepository rollRepository;
    private final TrainingRepository trainingRepository;
    private final AllUserTrainingAndRollsResponseBuilder allUserTrainingAndRollsResponseBuilder;
    private final AllRollsByUserTrainingIdResponseBuilder allRollsByUserTrainingIdResponseBuilder;

    @Override
    @Transactional
    public void createRoll(CreateRollRequest createRollRequest) {
        Training training = retrieveTrainingById(createRollRequest.getTrainingId());
        log.info("Creating roll for athlete_id: {}", training.getAthleteId());
        createRollRequest
            .getRolls()
            .forEach(roll ->
                rollRepository.save(
                    Roll
                        .builder()
                        .trainingId(training.getId())
                        .durationMinutes(roll.getDurationMinutes())
                        .intensity(roll.getIntensity())
                        .partnerName(roll.getPartnerName())
                        .partnerBelt(roll.getPartnerBelt())
                        .startPosition(
                            Optional
                                .ofNullable(roll.getStartPosition())
                                .orElse(StartPositionEnum.STANDING)
                        )
                        .submissionsApplied(
                            Optional.ofNullable(roll.getSubmissionsApplied()).orElse(0)
                        )
                        .submissionsSuffered(
                            Optional.ofNullable(roll.getSubmissionsSuffered()).orElse(0)
                        )
                        .sweeps(Optional.ofNullable(roll.getSweeps()).orElse(0))
                        .passes(Optional.ofNullable(roll.getPasses()).orElse(0))
                        .notes(roll.getNotes())
                        .build()
                )
            );
    }

    @Override
    public List<AllUserTrainingsAndRollsResponse> listAllUserRolls(UUID athleteId) {
        List<Training> trainings = trainingRepository.listAllTrainingByAthleteId(
            athleteId
        );

        return trainings
            .stream()
            .map(training -> {
                List<Roll> rolls = rollRepository.findByTrainingId(training.getId());
                return allUserTrainingAndRollsResponseBuilder.build(training, rolls);
            })
            .toList();
    }

    @Override
    public AllRollsByUserTrainingIdResponse listAllUserRollsByTrainingId(
        UUID trainingId,
        UUID athleteId
    ) {
        Training training = trainingRepository
            .findByIdAndAthleteId(trainingId, athleteId)
            .orElseThrow(TrainingNotFoundException::new);
        List<Roll> rolls = rollRepository.findByTrainingId(training.getId());

        return allRollsByUserTrainingIdResponseBuilder.builder(training, rolls);
    }

    private Training retrieveTrainingById(UUID trainingId) {
        return trainingRepository
            .findById(trainingId)
            .orElseThrow(TrainingNotFoundException::new);
    }
}
