package com.bjj_metrics_brasil.bjj.metrics.athlete.repository;

import com.bjj_metrics_brasil.bjj.metrics.athlete.repository.entity.Athlete;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRepository extends JpaRepository<Athlete, UUID> {
}
