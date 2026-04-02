package com.bjj_metrics_brasil.bjj.metrics.roll.repository;

import com.bjj_metrics_brasil.bjj.metrics.roll.repository.entity.Roll;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RollRepository extends JpaRepository<Roll, UUID> {
}
