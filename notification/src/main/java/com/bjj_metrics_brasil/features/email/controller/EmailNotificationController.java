package com.bjj_metrics_brasil.features.email.controller;

import com.bjj_metrics_brasil.features.email.model.request.SendNotificationEmailRequest;
import com.bjj_metrics_brasil.features.email.service.EmailNotificationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification/email")
@AllArgsConstructor
public class EmailNotificationController {

    private final EmailNotificationService emailNotificationService;

    @PostMapping("/send")
    public void sendEmail(@RequestBody SendNotificationEmailRequest sendNotificationEmailRequest) {

    }
}
