package com.bjj_metrics_brasil.features.account.service;

import com.bjj_metrics_brasil.features.account.model.request.SendCodeChangePasswordRequest;

public interface AccountService {
    void sendCodeChangePassword(
        SendCodeChangePasswordRequest sendCodeChangePasswordRequest
    );
}
