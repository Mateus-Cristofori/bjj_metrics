package com.bjj_metrics_brasil.features.email.service.impl;

import com.bjj_metrics_brasil.features.email.model.request.SendNotificationEmailRequest;
import com.bjj_metrics_brasil.features.email.service.EmailNotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailNotificationServiceImpl implements EmailNotificationService {

    @Override
    public void sendEmail(
        SendNotificationEmailRequest sendNotificationEmailRequest
    ) {

    }
}
