package com.bjj_metrics_brasil.tokenOtp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidateOtpRequest {

    private String email;
    private String otp;
}
