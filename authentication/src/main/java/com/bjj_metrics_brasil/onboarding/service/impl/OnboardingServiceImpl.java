package com.bjj_metrics_brasil.onboarding.service.impl;

import com.bjj_metrics_brasil.authentication.repository.UserRepository;
import com.bjj_metrics_brasil.authentication.repository.entity.Users;
import com.bjj_metrics_brasil.client.AppClient;
import com.bjj_metrics_brasil.exceptions.BadRequestException;
import com.bjj_metrics_brasil.onboarding.converter.CreateAcademyConverter;
import com.bjj_metrics_brasil.onboarding.converter.CreateAthleteConverter;
import com.bjj_metrics_brasil.onboarding.model.request.OnboardingUserRequest;
import com.bjj_metrics_brasil.onboarding.service.OnboardingService;
import com.bjj_metrics_brasil.onboarding.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class OnboardingServiceImpl implements OnboardingService {

    private final AppClient appClient;
    private final UserRepository userRepository;
    private final CreateAthleteConverter createAthleteConverter;
    private final CreateAcademyConverter createAcademyConverter;
    private final UserService userService;

    @Override
    public void onboardingUser(OnboardingUserRequest onboardingUserRequest) {
        log.info("Registering new user...");
        validateNewUser(onboardingUserRequest.getEmail());

        Users user = userService.createUser(onboardingUserRequest);
        try {
            appClient.createAthlete(
                createAthleteConverter.convert(user.getId(), onboardingUserRequest)
            );
            appClient.createAcademy(
                createAcademyConverter.convert(user.getId(), onboardingUserRequest)
            );
        } catch (RuntimeException e) {
            log.error(
                "An unexpected error occurred during user onboarding for userId: {}",
                user.getId()
            );
            userService.deleteUser(user.getId());
            throw e;
        }
    }

    private void validateNewUser(String email) {
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            log.error("Invalid user email: {}", email);
            throw new BadRequestException("Invalid email format");
        }
        if (isUserExists(email)) {
            log.error("User already exists for email: {}", email);
            throw new BadRequestException("User already exists");
        }
    }

    private boolean isUserExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
