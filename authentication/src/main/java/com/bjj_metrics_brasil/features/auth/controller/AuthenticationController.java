package com.bjj_metrics_brasil.features.auth.controller;

import com.bjj_metrics_brasil.features.auth.model.request.AuthenticationRequest;
import com.bjj_metrics_brasil.features.auth.model.response.AuthenticationResponse;
import com.bjj_metrics_brasil.features.auth.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public AuthenticationResponse login(
        @RequestBody @Valid AuthenticationRequest authenticationRequest
    ) {
        return authenticationService.login(authenticationRequest);
    }
}
