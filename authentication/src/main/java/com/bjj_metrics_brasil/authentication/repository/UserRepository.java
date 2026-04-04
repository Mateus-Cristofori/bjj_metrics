package com.bjj_metrics_brasil.authentication.repository;

import com.bjj_metrics_brasil.authentication.repository.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);
}
