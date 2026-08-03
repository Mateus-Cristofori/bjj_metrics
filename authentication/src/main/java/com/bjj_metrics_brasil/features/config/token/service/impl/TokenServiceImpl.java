package com.bjj_metrics_brasil.features.config.token.service.impl;

import com.bjj_metrics_brasil.exceptions.BadRequestException;
import com.bjj_metrics_brasil.features.auth.repository.entity.Users;
import com.bjj_metrics_brasil.features.config.token.config.JwtProperties;
import com.bjj_metrics_brasil.features.config.token.config.JwtTokenClaims;
import com.bjj_metrics_brasil.features.config.token.purpose.JwtTokenPurposeEnum;
import com.bjj_metrics_brasil.features.config.token.service.TokenService;
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
    public String generateToken(Users user, UUID athleteId) {
        Map<String, Object> claims = Map.of(
            JwtTokenClaims.USER_ID.name(),
            user.getId(),
            JwtTokenClaims.EMAIL.name(),
            user.getEmail(),
            JwtTokenClaims.ATHLETE_ID.name(),
            athleteId,
            JwtTokenClaims.PURPOSE.name(),
            JwtTokenPurposeEnum.ACCESS
        );
        return generateJwt(claims, jwtProperties.getExpiration());
    }

    @Override
    public String generateRefreshToken(Users user, UUID athleteId) {
        Map<String, Object> claims = Map.of(
            JwtTokenClaims.USER_ID.name(),
            user.getId(),
            JwtTokenClaims.EMAIL.name(),
            user.getEmail(),
            JwtTokenClaims.ATHLETE_ID.name(),
            athleteId,
            JwtTokenClaims.PURPOSE.name(),
            JwtTokenPurposeEnum.REFRESH
        );
        return generateJwt(claims, jwtProperties.getRefreshToken().getExpiration());
    }

    @Override
    public UUID getAthleteIdFromToken(String token) {
        Claims claims = Jwts
            .parser()
            .setSigningKey(
                Keys.hmacShaKeyFor(getSecret().getBytes(StandardCharsets.UTF_8))
            )
            .build()
            .parseClaimsJws(token)
            .getBody();

        return UUID.fromString(claims.get("ATHLETE_ID", String.class));
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

    @Override
    public String generatePasswordRecoveryToken(String email) {
        Map<String, Object> claims = Map.of(
            JwtTokenClaims.PURPOSE.name(),
            JwtTokenPurposeEnum.PASSWORD_RECOVERY.name(),
            JwtTokenClaims.EMAIL.name(),
            email
        );

        return generateJwt(
            claims,
            jwtProperties.getRecoveryPasswordToken().getExpiration()
        );
    }

    @Override
    public String getEmailFromPasswordRecoveryToken(String token) {
        Claims claims = parseToken(token);

        String purpose = claims.get(JwtTokenClaims.PURPOSE.name(), String.class);

        if (!JwtTokenPurposeEnum.PASSWORD_RECOVERY.name().equals(purpose)) {
            throw new BadRequestException("Token inválido.");
        }

        return claims.get(JwtTokenClaims.EMAIL.name(), String.class);
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

    private Claims parseToken(String token) {
        return Jwts
            .parser()
            .setSigningKey(
                Keys.hmacShaKeyFor(getSecret().getBytes(StandardCharsets.UTF_8))
            )
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
