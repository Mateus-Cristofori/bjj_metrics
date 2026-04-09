package com.bjj_metrics_brasil.training.service.impl;

import com.bjj_metrics_brasil.roll.repository.RollRepository;
import com.bjj_metrics_brasil.training.model.request.CreateTrainingRequest;
import com.bjj_metrics_brasil.training.model.response.AllUserTrainingsResponse;
import com.bjj_metrics_brasil.training.repository.TrainingRepository;
import com.bjj_metrics_brasil.training.repository.entity.Training;
import com.bjj_metrics_brasil.training.service.TrainingService;
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
    private final RollRepository rollRepository;

    @Override
    public void createTraining(
        UUID athleteId,
        CreateTrainingRequest createTrainingRequest
    ) {
        log.info("Creating training for user_id: {}", athleteId);
        trainingRepository.save(
            Training
                .builder()
                .athleteId(athleteId)
                .trainingDate(createTrainingRequest.getTrainingDate())
                .trainingType(createTrainingRequest.getTrainingType())
                .durationMinutes(createTrainingRequest.getDurationMinutes())
                .intensity(createTrainingRequest.getIntensity())
                .gi(createTrainingRequest.getGi())
                .rollsAmount(createTrainingRequest.getRollsAmount())
                .notes(createTrainingRequest.getNotes())
                .build()
        );
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
                    .rollsAmount(training.getRollsAmount())
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
}
