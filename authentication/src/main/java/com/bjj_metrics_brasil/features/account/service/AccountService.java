package com.bjj_metrics_brasil.features.account.service;

import com.bjj_metrics_brasil.features.account.model.request.SendCodeChangePasswordRequest;
import com.bjj_metrics_brasil.features.account.model.response.RetrieveUserAccountInfoResponse;
import java.util.UUID;

public interface AccountService {
    void sendCodeChangePassword(
        SendCodeChangePasswordRequest sendCodeChangePasswordRequest
    );
    RetrieveUserAccountInfoResponse retrieveUserAccountInfo(UUID athleteId);
}
