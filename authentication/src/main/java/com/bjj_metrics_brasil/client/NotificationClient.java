package com.bjj_metrics_brasil.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "notification-client", url = "${notification-client.feignUrl}")
public interface NotificationClient {
}
