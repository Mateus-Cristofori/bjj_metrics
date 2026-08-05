package com.bjj_metrics_brasil.features.account.service.impl;

import com.bjj_metrics_brasil.client.AppClient;
import com.bjj_metrics_brasil.client.model.response.RetrieveAthleteByUserIdResponse;
import com.bjj_metrics_brasil.exceptions.BadRequestException;
import com.bjj_metrics_brasil.exceptions.UserNotFoundException;
import com.bjj_metrics_brasil.features.account.model.request.ChangePasswordRequest;
import com.bjj_metrics_brasil.features.account.model.request.SendCodeChangePasswordRequest;
import com.bjj_metrics_brasil.features.account.model.request.VerifyTokenPasswordRecoveryRequest;
import com.bjj_metrics_brasil.features.account.model.response.RetrieveUserAccountInfoResponse;
import com.bjj_metrics_brasil.features.account.service.AccountService;
import com.bjj_metrics_brasil.features.auth.repository.UsersRepository;
import com.bjj_metrics_brasil.features.auth.repository.entity.Users;
import com.bjj_metrics_brasil.features.config.token.service.TokenService;
import com.bjj_metrics_brasil.tokenOtp.model.GenerateOtpRequest;
import com.bjj_metrics_brasil.tokenOtp.model.ValidateOtpRequest;
import com.bjj_metrics_brasil.tokenOtp.service.OtpService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UsersRepository usersRepository;
    private final AppClient appClient;
    private final OtpService otpService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void sendCodeChangePassword(
        SendCodeChangePasswordRequest sendCodeChangePasswordRequest
    ) {
        String email = sendCodeChangePasswordRequest.getEmail();

        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            log.error("Invalid user email: {}", email);
            throw new BadRequestException("Invalid email format");
        }

        usersRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

        otpService.generateOtp(GenerateOtpRequest.builder().email(email).build());
    }

    @Override
    public String verifyTokenPasswordRecovery(
        VerifyTokenPasswordRecoveryRequest verifyTokenPasswordRecoveryRequest
    ) {
        String email = verifyTokenPasswordRecoveryRequest.getEmail();
        String otp = verifyTokenPasswordRecoveryRequest.getCode();

        boolean isCodeValid = otpService.validateOtp(
            ValidateOtpRequest.builder().email(email).otp(otp).build()
        );

        if (!isCodeValid) {
            throw new BadRequestException("Código de recuperação inválido.");
        }

        return tokenService.generatePasswordRecoveryToken(email);
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        String email = tokenService.getEmailFromPasswordRecoveryToken(
            changePasswordRequest.getRecoveryToken()
        );

        Users user = usersRepository
            .findByEmail(email)
            .orElseThrow(UserNotFoundException::new);

        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));

        usersRepository.save(user);
    }

    @Override
    public RetrieveUserAccountInfoResponse retrieveUserAccountInfo(UUID userId) {
        RetrieveAthleteByUserIdResponse athleteInfo = appClient.retrieveAthleteByUserId(
            userId
        );

        return RetrieveUserAccountInfoResponse
            .builder()
            .username(athleteInfo.getAthleteName())
            .build();
    }
}
