package com.bjj_metrics_brasil.onboarding.service;

import com.bjj_metrics_brasil.authentication.repository.entity.Users;
import com.bjj_metrics_brasil.onboarding.model.request.OnboardingUserRequest;
import java.util.UUID;

public interface UserService {
    Users createUser(OnboardingUserRequest onboardingUserRequest);
    void deleteUser(UUID userId);
}
