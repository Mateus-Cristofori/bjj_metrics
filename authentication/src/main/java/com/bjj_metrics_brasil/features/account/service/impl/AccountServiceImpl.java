package com.bjj_metrics_brasil.features.account.service.impl;

import com.bjj_metrics_brasil.client.NotificationClient;
import com.bjj_metrics_brasil.features.account.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final NotificationClient notificationClient;
}
