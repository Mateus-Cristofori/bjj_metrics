package com.bjj_metrics_brasil.tokenOtp.service.impl;

import com.bjj_metrics_brasil.client.NotificationClient;
import com.bjj_metrics_brasil.client.model.Enum.EmailActionEnum;
import com.bjj_metrics_brasil.client.model.request.SendNotificationEmailRequest;
import com.bjj_metrics_brasil.exceptions.OtpMaxAttemptsException;
import com.bjj_metrics_brasil.service.RedisService;
import com.bjj_metrics_brasil.templates.model.Enum.TemplatesEnum;
import com.bjj_metrics_brasil.tokenOtp.model.GenerateOtpRequest;
import com.bjj_metrics_brasil.tokenOtp.model.ValidateOtpRequest;
import com.bjj_metrics_brasil.tokenOtp.service.OtpService;
import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final RedisService redisService;
    private final SecureRandom secureRandom = new SecureRandom();
    private final NotificationClient notificationClient;

    private static final String PREFIX_KEY = "pwd_reset:";
    private static final String PREFIX_ATTEMPTS = "pwd_reset_attempts:";
    private static final int MAX_ATTEMPTS = 3;
    private static final long EXPIRATION_MINUTES = 5;

    @Override
    public void generateOtp(GenerateOtpRequest generateOtpRequest) {
        int otpNumber = 100000 + secureRandom.nextInt(900000);
        String otp = String.valueOf(otpNumber);

        String email = generateOtpRequest.getEmail();
        String key = PREFIX_KEY + email;

        String attemptsKey = PREFIX_ATTEMPTS + email;

        redisService.save(key, otp, EXPIRATION_MINUTES, TimeUnit.MINUTES);

        redisService.delete(attemptsKey);

        notificationClient.sendEmail(
            SendNotificationEmailRequest
                .builder()
                .to(email)
                .subject("Recuperação de senha")
                .template(TemplatesEnum.FORGOT_PASSWORD)
                .action(EmailActionEnum.SIMPLE_MESSAGE)
                .templateData(Map.of("code", otp))
                .build()
        );
    }

    @Override
    public boolean validateOtp(ValidateOtpRequest validateOtpRequest) {
        String email = validateOtpRequest.getEmail();

        String key = PREFIX_KEY + email;
        String attemptsKey = PREFIX_ATTEMPTS + email;
        String attemptsStr = redisService.get(attemptsKey);

        int currentAttempts = attemptsStr != null ? Integer.parseInt(attemptsStr) : 0;

        if (currentAttempts >= MAX_ATTEMPTS) {
            redisService.delete(key);
            throw new OtpMaxAttemptsException();
        }

        String storedOtp = redisService.get(key);

        if (storedOtp == null) {
            return false;
        }

        if (!storedOtp.equals(validateOtpRequest.getOtp())) {
            currentAttempts++;
            redisService.save(
                attemptsKey,
                String.valueOf(currentAttempts),
                EXPIRATION_MINUTES,
                TimeUnit.MINUTES
            );
            return false;
        }

        redisService.delete(key);
        redisService.delete(attemptsKey);
        return true;
    }
}
