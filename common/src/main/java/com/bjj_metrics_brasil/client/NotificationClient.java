package com.bjj_metrics_brasil.client;

import com.bjj_metrics_brasil.client.model.request.SendNotificationEmailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-client-common", url = "${notification-client.feignUrl}")
public interface NotificationClient {
    @PostMapping("/api/v1/notification/email/send")
    void sendEmail(
        @RequestBody SendNotificationEmailRequest sendNotificationEmailRequest
    );
}
