package com.bjj_metrics_brasil.authentication.repository;

import com.bjj_metrics_brasil.authentication.repository.entity.Users;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByEmail(String email);
}
