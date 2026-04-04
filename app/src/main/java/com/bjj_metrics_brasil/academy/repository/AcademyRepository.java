package com.bjj_metrics_brasil.academy.repository;

import com.bjj_metrics_brasil.academy.repository.entity.Academy;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademyRepository extends JpaRepository<Academy, UUID> {
}
