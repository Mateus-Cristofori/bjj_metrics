package com.bjj_metrics_brasil.training.service.impl;

import com.bjj_metrics_brasil.roll.model.request.CreateRollRequest;
import com.bjj_metrics_brasil.roll.service.RollService;
import com.bjj_metrics_brasil.training.model.request.CreateTrainingRequest;
import com.bjj_metrics_brasil.training.model.request.CreateTrainingWithRollsRequest;
import com.bjj_metrics_brasil.training.model.response.AllUserTrainingsResponse;
import com.bjj_metrics_brasil.training.repository.TrainingRepository;
import com.bjj_metrics_brasil.training.repository.entity.Training;
import com.bjj_metrics_brasil.training.service.TrainingService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final RollService rollService;

    @Override
    public UUID createTraining(
        UUID athleteId,
        CreateTrainingRequest createTrainingRequest
    ) {
        log.info("Creating training for athlete_id: {}", athleteId);
        return trainingRepository
            .save(
                Training
                    .builder()
                    .athleteId(athleteId)
                    .trainingDate(createTrainingRequest.getTrainingDate())
                    .trainingType(createTrainingRequest.getTrainingType())
                    .durationMinutes(createTrainingRequest.getDurationMinutes())
                    .intensity(createTrainingRequest.getIntensity())
                    .gi(createTrainingRequest.getGi())
                    .notes(createTrainingRequest.getNotes())
                    .athletePerformance(createTrainingRequest.getAthletePerformance())
                    .build()
            )
            .getId();
    }

    @Override
    public List<AllUserTrainingsResponse> listAllUserTrainings(UUID athleteId) {
        List<Training> allUserTraining = trainingRepository.listAllTrainingByAthleteId(
            athleteId
        );

        return allUserTraining
            .stream()
            .map(training ->
                AllUserTrainingsResponse
                    .builder()
                    .trainingId(training.getId())
                    .trainingType(training.getTrainingType().name())
                    .durationMinutes(training.getDurationMinutes())
                    .intensity(training.getIntensity().name())
                    .gi(training.getGi())
                    .academyId(training.getAcademyId())
                    .notes(training.getNotes())
                    .trainingDate(training.getTrainingDate())
                    .build()
            )
            .toList();
    }

    @Override
    public AllUserTrainingsResponse retrieveByTrainingId(UUID trainingId) {
        return null;
    }

    @Override
    @Transactional
    public void createTrainingWithRolls(
        UUID athleteId,
        CreateTrainingWithRollsRequest createTrainingWithRollsRequest
    ) {
        log.info("Creating training with rolls for athlete_id: {}", athleteId);
        UUID trainingId = createTraining(
            athleteId,
            createTrainingWithRollsRequest.getTrainingRequest()
        );
        log.info("Creating training rolls for training_id: {}", trainingId);
        rollService.createRoll(
            CreateRollRequest
                .builder()
                .trainingId(trainingId)
                .rolls(createTrainingWithRollsRequest.getRollRequest().getRolls())
                .build()
        );
    }
}
