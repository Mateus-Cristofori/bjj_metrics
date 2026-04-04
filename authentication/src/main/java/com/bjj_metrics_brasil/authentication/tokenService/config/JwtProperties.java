package com.bjj_metrics_brasil.authentication.tokenService.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "spring.application.jwt")
@Configuration
@Getter
@Setter
public class JwtProperties {

    private String secret;
    private Long expiration;
    private RefreshToken refreshToken;

    @Getter
    @Setter
    public static class RefreshToken {

        private Long expiration;
    }
}
