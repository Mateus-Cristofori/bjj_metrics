package com.bjj_metrics_brasil.features.auth.repository;

import com.bjj_metrics_brasil.features.auth.repository.entity.Users;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByEmail(String email);
}
