package com.bjj_metrics_brasil.features.auth.service.impl;

import com.bjj_metrics_brasil.client.AppClient;
import com.bjj_metrics_brasil.exceptions.BadRequestException;
import com.bjj_metrics_brasil.exceptions.InvalidUserCredentialsException;
import com.bjj_metrics_brasil.exceptions.UserNotFoundException;
import com.bjj_metrics_brasil.features.auth.model.request.AuthenticationRequest;
import com.bjj_metrics_brasil.features.auth.model.request.RefreshTokenRequest;
import com.bjj_metrics_brasil.features.auth.model.response.AuthenticationResponse;
import com.bjj_metrics_brasil.features.auth.model.response.RefreshTokenResponse;
import com.bjj_metrics_brasil.features.auth.repository.UsersRepository;
import com.bjj_metrics_brasil.features.auth.repository.entity.Users;
import com.bjj_metrics_brasil.features.auth.service.AuthenticationService;
import com.bjj_metrics_brasil.features.config.token.config.JwtTokenClaims;
import com.bjj_metrics_brasil.features.config.token.purpose.JwtTokenPurposeEnum;
import com.bjj_metrics_brasil.features.config.token.service.TokenService;
import io.jsonwebtoken.Claims;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UsersRepository usersRepository;
    private final TokenService tokenService;
    private final AppClient appClient;

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        Users user = usersRepository
            .findByEmail(authenticationRequest.getEmail())
            .orElseThrow(UserNotFoundException::new);

        authenticateUser(buildUsernamePasswordAuthentication(authenticationRequest));

        UUID athleteId = appClient.retrieveAthleteByUserId(user.getId()).getId();

        String accessToken = tokenService.generateToken(user, athleteId);
        String refreshToken = tokenService.generateRefreshToken(user, athleteId);

        return AuthenticationResponse
            .builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
    }

    @Override
    public RefreshTokenResponse refresh(RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();
        Claims claims = tokenService.parseToken(refreshToken);

        String purpose = claims.get(JwtTokenClaims.PURPOSE.name(), String.class);

        if (!JwtTokenPurposeEnum.REFRESH.name().equals(purpose)) {
            throw new BadRequestException("Refresh token inválido.");
        }

        UUID userId = UUID.fromString(
            claims.get(JwtTokenClaims.USER_ID.name(), String.class)
        );

        UUID athleteId = UUID.fromString(
            claims.get(JwtTokenClaims.ATHLETE_ID.name(), String.class)
        );

        Users user = usersRepository
            .findById(userId)
            .orElseThrow(UserNotFoundException::new);

        String accessToken = tokenService.generateToken(user, athleteId);

        return new RefreshTokenResponse(accessToken);
    }

    private void authenticateUser(
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
    ) {
        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (RuntimeException e) {
            throw new InvalidUserCredentialsException();
        }
    }

    private UsernamePasswordAuthenticationToken buildUsernamePasswordAuthentication(
        AuthenticationRequest authenticationRequest
    ) {
        return new UsernamePasswordAuthenticationToken(
            authenticationRequest.getEmail(),
            authenticationRequest.getPassword()
        );
    }
}
