package com.bjj_metrics_brasil.training.repository;

import com.bjj_metrics_brasil.statistics.projection.model.GiStatsProjection;
import com.bjj_metrics_brasil.statistics.projection.model.TrainingStatsProjection;
import com.bjj_metrics_brasil.training.repository.entity.Training;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TrainingRepository extends JpaRepository<Training, UUID> {
    @Query(value = "SELECT t FROM Training t WHERE t.athleteId = :athleteId")
    List<Training> listAllTrainingByAthleteId(UUID athleteId);

    Training findByIdAndAthleteId(UUID id, UUID athleteId);

    @Query(
        """
    SELECT
        COUNT(t) as totalTrainings,
        AVG(t.durationMinutes) as averageDuration
    FROM Training t
    WHERE t.athleteId = :athleteId
"""
    )
    TrainingStatsProjection getTrainingStats(UUID athleteId);

    @Query(
        """
    SELECT
        SUM(CASE WHEN t.gi = true THEN 1 ELSE 0 END) as giCount,
        SUM(CASE WHEN t.gi = false THEN 1 ELSE 0 END) as noGiCount
    FROM Training t
    WHERE t.athleteId = :athleteId
"""
    )
    GiStatsProjection getGiStats(UUID athleteId);

    long countByAthleteId(UUID athleteId);
}
