package com.bjj_metrics_brasil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BjjMetricsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BjjMetricsApplication.class, args);
    }
}
