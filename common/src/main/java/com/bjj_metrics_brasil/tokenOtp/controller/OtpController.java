package com.bjj_metrics_brasil.tokenOtp.controller;

import com.bjj_metrics_brasil.tokenOtp.model.GenerateOtpRequest;
import com.bjj_metrics_brasil.tokenOtp.model.ValidateOtpRequest;
import com.bjj_metrics_brasil.tokenOtp.service.OtpService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/otp")
@AllArgsConstructor
public class OtpController {

    private final OtpService otpService;

    @PostMapping("/generate")
    public void generateOtp(@RequestBody GenerateOtpRequest generateOtpRequest) {
        otpService.generateOtp(generateOtpRequest);
    }

    @PostMapping("/validate")
    public boolean validateOtp(@RequestBody ValidateOtpRequest validateOtpRequest) {
        return otpService.validateOtp(validateOtpRequest);
    }
}
