package com.bjj_metrics_brasil.bjj.metrics.authentication.repository;

import com.bjj_metrics_brasil.bjj.metrics.authentication.repository.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
}
