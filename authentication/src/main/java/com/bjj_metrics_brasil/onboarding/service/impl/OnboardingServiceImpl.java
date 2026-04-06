package com.bjj_metrics_brasil.onboarding.service.impl;

import com.bjj_metrics_brasil.authentication.repository.Enum.UserStatusEnum;
import com.bjj_metrics_brasil.authentication.repository.UserRepository;
import com.bjj_metrics_brasil.authentication.repository.entity.User;
import com.bjj_metrics_brasil.client.AppClient;
import com.bjj_metrics_brasil.exceptions.BadRequestException;
import com.bjj_metrics_brasil.onboarding.converter.CreateAthleteConverter;
import com.bjj_metrics_brasil.onboarding.model.request.OnboardingUserRequest;
import com.bjj_metrics_brasil.onboarding.service.OnboardingService;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class OnboardingServiceImpl implements OnboardingService {

    private final AppClient appClient;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CreateAthleteConverter createAthleteConverter;

    @Override
    @Transactional
    public void onboardingUser(OnboardingUserRequest onboardingUserRequest) {
        validateNewUser(onboardingUserRequest.getEmail());

        User user = userRepository.save(
            User
                .builder()
                .email(onboardingUserRequest.getEmail())
                .password(passwordEncoder.encode(onboardingUserRequest.getPassword()))
                .status(UserStatusEnum.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build()
        );

        appClient.createAthlete(
            createAthleteConverter.convert(user.getId(), onboardingUserRequest)
        );
    }

    private void validateNewUser(String email) {
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new BadRequestException("Invalid email format");
        }
        if (isUserExists(email)) {
            throw new BadRequestException("User already exists");
        }
    }

    private boolean isUserExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
