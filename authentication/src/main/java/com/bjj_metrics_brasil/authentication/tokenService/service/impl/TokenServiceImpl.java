package com.bjj_metrics_brasil.authentication.tokenService.service.impl;

import com.bjj_metrics_brasil.authentication.repository.entity.User;
import com.bjj_metrics_brasil.authentication.tokenService.config.JwtProperties;
import com.bjj_metrics_brasil.authentication.tokenService.config.JwtTokenClaims;
import com.bjj_metrics_brasil.authentication.tokenService.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtProperties jwtProperties;

    @Override
    public String generateToken(User user) {
        Map<String, Object> claims = Map.of(JwtTokenClaims.USER_ID.name(), user.getId(),
                JwtTokenClaims.EMAIL.name(), user.getEmail());
        return generateJwt(claims, jwtProperties.getExpiration());
    }

    @Override
    public String generateRefreshToken(User user) {
        Map<String, Object> claims = Map.of(JwtTokenClaims.USER_ID.name(), user.getId(),
                JwtTokenClaims.EMAIL.name(), user.getEmail());
        return generateJwt(claims, jwtProperties.getRefreshToken().getExpiration());
    }

    private String generateJwt(Map<String, Object> claims, Long expiration) {
        Date now = new Date(System.currentTimeMillis());
        Date expiresIn = new Date(now.getTime() + expiration);
        return Jwts.builder().issuedAt(now).expiration(expiresIn).claims(claims)
                .signWith(Keys.hmacShaKeyFor(getSecret().getBytes())).compact();
    }

    private String getSecret() {
        return jwtProperties.getSecret();
    }
}
