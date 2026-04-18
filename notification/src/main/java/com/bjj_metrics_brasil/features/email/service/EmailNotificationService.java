package com.bjj_metrics_brasil.features.email.service;

import com.bjj_metrics_brasil.features.email.model.request.SendNotificationEmailRequest;

public interface EmailNotificationService {
    void sendEmail(SendNotificationEmailRequest sendNotificationEmailRequest);
}
