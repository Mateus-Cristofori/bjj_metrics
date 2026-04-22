package com.bjj_metrics_brasil.training.repository;

import com.bjj_metrics_brasil.statistics.projection.model.GiStatsProjection;
import com.bjj_metrics_brasil.statistics.projection.model.TrainingSequenceProjection;
import com.bjj_metrics_brasil.statistics.projection.model.TrainingStatsProjection;
import com.bjj_metrics_brasil.statistics.projection.model.WeeklyTrainingProjection;
import com.bjj_metrics_brasil.training.repository.entity.Training;
import java.time.LocalDate;
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

    @Query(
        """
SELECT
  t.trainingDate as date,
  COUNT(t) as total
FROM Training t
WHERE t.athleteId = :athleteId
  AND t.trainingDate >= :startDate
GROUP BY t.trainingDate
ORDER BY t.trainingDate
"""
    )
    List<WeeklyTrainingProjection> getWeeklyTrainings(
        UUID athleteId,
        LocalDate startDate
    );

    @Query(
        value = """
SELECT
  EXTRACT(YEAR FROM t.training_date) AS year,
  EXTRACT(WEEK FROM t.training_date) AS week,
  COUNT(t.id) AS total
FROM training t
WHERE t.athlete_id = :athleteId
GROUP BY 1,2
ORDER BY 1 DESC, 2 DESC
""",
        nativeQuery = true
    )
    List<TrainingSequenceProjection> getLastWeeks(UUID athleteId);

    List<Training> findByAthleteId(UUID athleteId);
}
