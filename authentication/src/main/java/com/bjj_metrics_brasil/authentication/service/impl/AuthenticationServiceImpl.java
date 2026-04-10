package com.bjj_metrics_brasil.authentication.service.impl;

import com.bjj_metrics_brasil.authentication.config.token.service.TokenService;
import com.bjj_metrics_brasil.authentication.model.request.AuthenticationRequest;
import com.bjj_metrics_brasil.authentication.model.response.AuthenticationResponse;
import com.bjj_metrics_brasil.authentication.repository.UsersRepository;
import com.bjj_metrics_brasil.authentication.repository.entity.Users;
import com.bjj_metrics_brasil.authentication.service.AuthenticationService;
import com.bjj_metrics_brasil.client.AppClient;
import com.bjj_metrics_brasil.exceptions.InvalidUserCredentialsException;
import com.bjj_metrics_brasil.exceptions.UserNotFoundException;
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

        String token = tokenService.generateToken(user, athleteId);
        String refreshToken = tokenService.generateRefreshToken(user, athleteId);

        return AuthenticationResponse
            .builder()
            .token(token)
            .refreshToken(refreshToken)
            .build();
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
