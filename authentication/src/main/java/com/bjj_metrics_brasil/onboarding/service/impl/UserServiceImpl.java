package com.bjj_metrics_brasil.onboarding.service.impl;

import com.bjj_metrics_brasil.authentication.repository.Enum.UserStatusEnum;
import com.bjj_metrics_brasil.authentication.repository.UsersRepository;
import com.bjj_metrics_brasil.authentication.repository.entity.Users;
import com.bjj_metrics_brasil.onboarding.model.request.OnboardingUserRequest;
import com.bjj_metrics_brasil.onboarding.service.UserService;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Users createUser(OnboardingUserRequest onboardingUserRequest) {
        return usersRepository.save(
            Users
                .builder()
                .email(onboardingUserRequest.getEmail())
                .password(passwordEncoder.encode(onboardingUserRequest.getPassword()))
                .status(UserStatusEnum.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build()
        );
    }

    @Override
    public void deleteUser(UUID userId) {
        log.info("Deleting user: {}", userId);
        usersRepository.deleteById(userId);
    }
}
