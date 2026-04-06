package com.bjj_metrics_brasil.onboarding.controller;

import com.bjj_metrics_brasil.onboarding.model.request.OnboardingUserRequest;
import com.bjj_metrics_brasil.onboarding.service.OnboardingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/onboarding")
@AllArgsConstructor
public class OnboardingController {

    private final OnboardingService onboardingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void onboardingUser(
        @RequestBody @Valid OnboardingUserRequest onboardingUserRequest
    ) {
        onboardingService.onboardingUser(onboardingUserRequest);
    }
}
