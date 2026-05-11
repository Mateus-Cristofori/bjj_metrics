package com.bjj_metrics_brasil.features.account.service.impl;

import com.bjj_metrics_brasil.client.AppClient;
import com.bjj_metrics_brasil.client.NotificationClient;
import com.bjj_metrics_brasil.client.model.response.RetrieveAthleteByUserIdResponse;
import com.bjj_metrics_brasil.features.account.model.request.SendCodeChangePasswordRequest;
import com.bjj_metrics_brasil.features.account.model.response.RetrieveUserAccountInfoResponse;
import com.bjj_metrics_brasil.features.account.service.AccountService;
import com.bjj_metrics_brasil.features.auth.repository.UsersRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UsersRepository usersRepository;
    private final NotificationClient notificationClient;
    private final AppClient appClient;

    @Override
    public void sendCodeChangePassword(
        SendCodeChangePasswordRequest sendCodeChangePasswordRequest
    ) {}

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
