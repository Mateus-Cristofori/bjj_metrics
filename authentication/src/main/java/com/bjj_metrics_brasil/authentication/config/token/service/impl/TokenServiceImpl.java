package com.bjj_metrics_brasil.authentication.config.token.service.impl;

import com.bjj_metrics_brasil.authentication.config.token.config.JwtProperties;
import com.bjj_metrics_brasil.authentication.config.token.config.JwtTokenClaims;
import com.bjj_metrics_brasil.authentication.config.token.service.TokenService;
import com.bjj_metrics_brasil.authentication.repository.entity.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtProperties jwtProperties;

    @Override
    public String generateToken(Users user) {
        Map<String, Object> claims = Map.of(
            JwtTokenClaims.USER_ID.name(),
            user.getId(),
            JwtTokenClaims.EMAIL.name(),
            user.getEmail()
        );
        return generateJwt(claims, jwtProperties.getExpiration());
    }

    @Override
    public String generateRefreshToken(Users user) {
        Map<String, Object> claims = Map.of(
            JwtTokenClaims.USER_ID.name(),
            user.getId(),
            JwtTokenClaims.EMAIL.name(),
            user.getEmail()
        );
        return generateJwt(claims, jwtProperties.getRefreshToken().getExpiration());
    }

    @Override
    public UUID getUserIdFromToken(String token) {
        Claims claims = Jwts
            .parser()
            .setSigningKey(
                Keys.hmacShaKeyFor(getSecret().getBytes(StandardCharsets.UTF_8))
            )
            .build()
            .parseClaimsJws(token)
            .getBody();

        return UUID.fromString(claims.get("USER_ID", String.class));
    }

    private String generateJwt(Map<String, Object> claims, Long expiration) {
        Date now = new Date(System.currentTimeMillis());
        Date expiresIn = new Date(now.getTime() + expiration);
        return Jwts
            .builder()
            .issuedAt(now)
            .expiration(expiresIn)
            .claims(claims)
            .signWith(Keys.hmacShaKeyFor(getSecret().getBytes()))
            .compact();
    }

    private String getSecret() {
        return jwtProperties.getSecret();
    }
}
