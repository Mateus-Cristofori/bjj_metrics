package com.bjj_metrics_brasil.features.account.controller;

import com.bjj_metrics_brasil.features.account.model.request.SendCodeChangePasswordRequest;
import com.bjj_metrics_brasil.features.account.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/send/code/change-password")
    public void sendCodeChangePassword(
        @RequestBody @Valid SendCodeChangePasswordRequest sendCodeChangePasswordRequest
    ) {}

    @PatchMapping("/change")
    public void changePassword() {}
}
