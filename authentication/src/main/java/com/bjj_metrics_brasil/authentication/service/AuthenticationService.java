package com.bjj_metrics_brasil.authentication.service;

import com.bjj_metrics_brasil.authentication.model.request.AuthenticationRequest;
import com.bjj_metrics_brasil.authentication.model.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}
