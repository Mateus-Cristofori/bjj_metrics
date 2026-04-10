package com.bjj_metrics_brasil.fight.repository;

import com.bjj_metrics_brasil.fight.repository.entity.Fight;
import com.bjj_metrics_brasil.statistics.projection.model.FightStatsProjection;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FightRepository extends JpaRepository<Fight, UUID> {
    List<Fight> findAllByAthleteId(UUID athleteId);

    @Query(
        """
    SELECT
        COUNT(f) as totalFights,
        SUM(CASE WHEN f.result = 'WIN' THEN 1 ELSE 0 END) as wins
    FROM Fight f
    WHERE f.athleteId = :athleteId
"""
    )
    FightStatsProjection getFightStats(UUID athleteId);

    @Query(
        """
    SELECT AVG(f.fightDurationInMinutes)
    FROM Fight f
    WHERE f.athleteId = :athleteId
"""
    )
    Double getAverageDuration(UUID athleteId);
}
