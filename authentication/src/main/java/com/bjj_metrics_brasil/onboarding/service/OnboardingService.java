package com.bjj_metrics_brasil.onboarding.service;

import com.bjj_metrics_brasil.onboarding.model.request.OnboardingUserRequest;

public interface OnboardingService {
    void onboardingUser(
        OnboardingUserRequest onboardingUserRequest
    );
}
