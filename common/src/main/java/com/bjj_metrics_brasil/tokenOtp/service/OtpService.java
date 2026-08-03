package com.bjj_metrics_brasil.tokenOtp.service;

import com.bjj_metrics_brasil.tokenOtp.model.GenerateOtpRequest;
import com.bjj_metrics_brasil.tokenOtp.model.ValidateOtpRequest;

public interface OtpService {
    void generateOtp(GenerateOtpRequest generateOtpRequest);
    boolean validateOtp(ValidateOtpRequest validateOtpRequest);
}
