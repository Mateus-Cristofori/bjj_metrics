package com.bjj_metrics_brasil.features.email.service;

import com.bjj_metrics_brasil.features.email.model.request.SendNotificationEmailRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface EmailNotificationService {
    void sendEmail(@RequestBody SendNotificationEmailRequest sendNotificationEmailRequest);
}
