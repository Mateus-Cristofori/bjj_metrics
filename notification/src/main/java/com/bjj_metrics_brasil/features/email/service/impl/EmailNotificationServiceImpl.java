package com.bjj_metrics_brasil.features.email.service.impl;

import com.bjj_metrics_brasil.features.config.EmailConfig;
import com.bjj_metrics_brasil.features.email.model.request.SendNotificationEmailRequest;
import com.bjj_metrics_brasil.features.email.service.EmailNotificationService;
import com.bjj_metrics_brasil.features.exceptions.EmailActionNotSupportedException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class EmailNotificationServiceImpl implements EmailNotificationService {

    private static final String MAIL_SENDER_ENCODING = "UTF-8";

    private final EmailConfig emailConfig;
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Override
    public void sendEmail(SendNotificationEmailRequest sendNotificationEmailRequest) {
        switch (sendNotificationEmailRequest.getAction()) {
            case SIMPLE_MESSAGE -> sendEmailWithoutAttachment(
                sendNotificationEmailRequest
            );
            default -> throw new EmailActionNotSupportedException();
        }
    }

    private void sendEmailWithoutAttachment(SendNotificationEmailRequest request) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                message,
                MAIL_SENDER_ENCODING
            );

            String html = processTemplate(request);

            helper.setFrom(emailConfig.getUsername());
            helper.setTo(request.getTo());
            helper.setSubject(request.getSubject());
            helper.setText(html, true);

            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Error sending email!", e);
        }
    }

    private String processTemplate(SendNotificationEmailRequest request) {
        Context context = new Context();

        // ! Manipulação de variáveis dos templates html.
        return templateEngine.process(request.getTemplate().getValue(), context);
    }
}
