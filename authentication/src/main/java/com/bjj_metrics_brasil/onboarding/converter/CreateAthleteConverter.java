package com.bjj_metrics_brasil.onboarding.converter;

import com.bjj_metrics_brasil.client.model.request.CreateAthleteRequest;
import com.bjj_metrics_brasil.onboarding.model.request.OnboardingUserRequest;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CreateAthleteConverter {

    public CreateAthleteRequest convert(
        UUID userId,
        OnboardingUserRequest onboardingUserRequest
    ) {
        return CreateAthleteRequest
            .builder()
            .userId(userId)
            .name(onboardingUserRequest.getName())
            .lastname(onboardingUserRequest.getLastname())
            .email(onboardingUserRequest.getEmail())
            .belt(onboardingUserRequest.getBelt())
            .weight(onboardingUserRequest.getWeight())
            .birthDate(onboardingUserRequest.getBirthDate())
            .build();
    }
}
