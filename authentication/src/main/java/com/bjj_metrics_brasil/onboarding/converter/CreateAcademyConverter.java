package com.bjj_metrics_brasil.onboarding.converter;

import com.bjj_metrics_brasil.client.model.request.CreateAcademyRequest;
import com.bjj_metrics_brasil.onboarding.model.request.OnboardingUserRequest;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CreateAcademyConverter {

    public CreateAcademyRequest convert(
        UUID userId,
        OnboardingUserRequest onboardingUserRequest
    ) {
        return CreateAcademyRequest
            .builder()
            .userId(userId)
            .academyName(onboardingUserRequest.getAcademy().getAcademyName())
            .city(onboardingUserRequest.getAcademy().getCity())
            .state(onboardingUserRequest.getAcademy().getState())
            .country(onboardingUserRequest.getAcademy().getCountry())
            .build();
    }
}
