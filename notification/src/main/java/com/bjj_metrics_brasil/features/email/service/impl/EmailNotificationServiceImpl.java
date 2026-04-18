package com.bjj_metrics_brasil.features.email.service.impl;

import com.bjj_metrics_brasil.features.email.model.request.SendNotificationEmailRequest;
import com.bjj_metrics_brasil.features.email.service.EmailNotificationService;
import com.bjj_metrics_brasil.features.exceptions.EmailActionNotSupportedException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailNotificationServiceImpl implements EmailNotificationService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(SendNotificationEmailRequest sendNotificationEmailRequest) {
        switch (sendNotificationEmailRequest.getAction()) {
            case SIMPLE_MESSAGE -> sendSimpleMessageEmail();
            case WITH_ATTACHMENT -> sendEmailWithAttachment();
            default -> throw new EmailActionNotSupportedException();
        }
    }

    private void sendSimpleMessageEmail() {}

    private void sendEmailWithAttachment() {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
    }
}
