package com.bjj_metrics_brasil.features.auth.service;

import com.bjj_metrics_brasil.features.auth.model.request.AuthenticationRequest;
import com.bjj_metrics_brasil.features.auth.model.request.RefreshTokenRequest;
import com.bjj_metrics_brasil.features.auth.model.response.AuthenticationResponse;
import com.bjj_metrics_brasil.features.auth.model.response.RefreshTokenResponse;

public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
    RefreshTokenResponse refresh(RefreshTokenRequest refreshTokenRequest);
}
