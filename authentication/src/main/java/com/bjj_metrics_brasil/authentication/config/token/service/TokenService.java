package com.bjj_metrics_brasil.authentication.config.token.service;

import com.bjj_metrics_brasil.authentication.repository.entity.User;

public interface TokenService {

    String generateToken(User user);
    String generateRefreshToken(User user);
}
