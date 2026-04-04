package com.bjj_metrics_brasil.training.repository;

import com.bjj_metrics_brasil.training.repository.entity.Training;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, UUID> {
}
