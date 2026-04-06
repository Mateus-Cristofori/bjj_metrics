package com.bjj_metrics_brasil.authentication.config.token.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.application.jwt")
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
