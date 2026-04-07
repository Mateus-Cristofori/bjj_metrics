package com.bjj_metrics_brasil;

import exception.handler.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableFeignClients
@ConfigurationPropertiesScan
@Import(GlobalExceptionHandler.class)
public class BjjMetricsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BjjMetricsApplication.class, args);
    }
}
