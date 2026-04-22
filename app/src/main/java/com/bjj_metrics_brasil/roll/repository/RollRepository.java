package com.bjj_metrics_brasil.roll.repository;

import com.bjj_metrics_brasil.roll.repository.entity.Roll;
import com.bjj_metrics_brasil.statistics.projection.model.BeltStatsProjection;
import com.bjj_metrics_brasil.statistics.projection.model.GameStatsProjection;
import com.bjj_metrics_brasil.statistics.projection.model.SubmissionStatsProjection;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RollRepository extends JpaRepository<Roll, UUID> {
    List<Roll> findByTrainingId(UUID trainingId);

    @Query(
        """
    SELECT COUNT(r)
    FROM Roll r
    JOIN Training t ON r.trainingId = t.id
    WHERE t.athleteId = :athleteId
"""
    )
    long countRolls(UUID athleteId);

    @Query(
        """
    SELECT
        SUM(r.submissionsApplied) as submissionsApplied,
        SUM(r.submissionsSuffered) as submissionsSuffered
    FROM Roll r
    JOIN Training t ON r.trainingId = t.id
    WHERE t.athleteId = :athleteId
"""
    )
    SubmissionStatsProjection getSubmissionStats(UUID athleteId);

    @Query(
        """
    SELECT
        SUM(r.sweeps) as sweeps,
        SUM(r.passes) as passes
    FROM Roll r
    JOIN Training t ON r.trainingId = t.id
    WHERE t.athleteId = :athleteId
"""
    )
    GameStatsProjection getGameStats(UUID athleteId);

    @Query(
        """
    SELECT
        AVG(
            CASE
                WHEN r.intensity = 'LOW' THEN 1
                WHEN r.intensity = 'MEDIUM_INTENSITY' THEN 2
                WHEN r.intensity = 'HIGH' THEN 3
                ELSE NULL
            END
        )
    FROM Roll r
    JOIN Training t ON r.trainingId = t.id
    WHERE t.athleteId = :athleteId
"""
    )
    Double getAverageIntensity(UUID athleteId);

    List<Roll> findByAthleteId(UUID athleteId);

    @Query(
        """
SELECT r.partnerBelt as belt, COUNT(r) as total
FROM Roll r
JOIN Training t ON t.id = r.trainingId
WHERE t.athleteId = :athleteId
GROUP BY r.partnerBelt
"""
    )
    List<BeltStatsProjection> getBeltStats(UUID athleteId);
}
