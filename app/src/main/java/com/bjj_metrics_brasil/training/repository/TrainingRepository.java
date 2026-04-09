package com.bjj_metrics_brasil.training.repository;

import com.bjj_metrics_brasil.training.repository.entity.Training;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TrainingRepository extends JpaRepository<Training, UUID> {
    @Query(value = "SELECT t FROM Training t WHERE t.athleteId = :athleteId")
    List<Training> listAllTrainingByAthleteId(UUID athleteId);

    Training findByIdAndAthleteId(UUID id, UUID athleteId);
}
