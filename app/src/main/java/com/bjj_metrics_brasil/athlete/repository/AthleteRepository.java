package com.bjj_metrics_brasil.athlete.repository;

import com.bjj_metrics_brasil.athlete.repository.entity.Athlete;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRepository extends JpaRepository<Athlete, UUID> {
}
