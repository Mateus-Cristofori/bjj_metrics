package com.bjj_metrics_brasil.features.config.token.service;

import com.bjj_metrics_brasil.features.auth.repository.entity.Users;
import java.util.UUID;

public interface TokenService {
    String generateToken(Users user, UUID athleteId);
    String generateRefreshToken(Users user, UUID athleteId);
    UUID getAthleteIdFromToken(String token);
}
