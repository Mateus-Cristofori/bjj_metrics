package com.bjj_metrics_brasil.authentication.service.impl;

import com.bjj_metrics_brasil.authentication.config.token.service.TokenService;
import com.bjj_metrics_brasil.authentication.model.request.AuthenticationRequest;
import com.bjj_metrics_brasil.authentication.model.response.AuthenticationResponse;
import com.bjj_metrics_brasil.authentication.repository.UserRepository;
import com.bjj_metrics_brasil.authentication.repository.entity.Users;
import com.bjj_metrics_brasil.authentication.service.AuthenticationService;
import com.bjj_metrics_brasil.exceptions.InvalidUserCredentialsException;
import com.bjj_metrics_brasil.exceptions.UserNotFoundException;
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
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        Users user = userRepository
            .findByEmail(authenticationRequest.getEmail())
            .orElseThrow(UserNotFoundException::new);

        authenticateUser(buildUsernamePasswordAuthentication(authenticationRequest));

        String token = tokenService.generateToken(user);
        String refreshToken = tokenService.generateRefreshToken(user);

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
