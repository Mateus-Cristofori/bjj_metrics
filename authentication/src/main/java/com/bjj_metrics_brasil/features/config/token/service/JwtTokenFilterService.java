package com.bjj_metrics_brasil.features.config.token.service;

import com.bjj_metrics_brasil.features.config.ObjectMapperConfiguration;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtTokenFilterService extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ObjectMapperConfiguration objectMapper;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);
        try {
            UUID athleteId = tokenService.getAthleteIdFromToken(token);
            UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(athleteId, null, List.of());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException ex) {
            writeErrorResponse(
                request,
                response,
                "Token has expired. Please log in again.",
                HttpStatus.UNAUTHORIZED
            );
        } catch (Exception e) {
            writeErrorResponse(
                request,
                response,
                "Invalid token. Please log in again.",
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    private void writeErrorResponse(
        HttpServletRequest request,
        HttpServletResponse httpResponse,
        String message,
        HttpStatus httpStatus
    ) throws IOException {
        JwtErrorResponse jwtErrorResponse = new JwtErrorResponse(
            request.getRequestURI(),
            message,
            httpStatus.value()
        );

        httpResponse.setStatus(httpStatus.value());
        httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

        objectMapper
            .objectMapper()
            .writeValue(httpResponse.getWriter(), jwtErrorResponse);
    }

    private record JwtErrorResponse(String path, String message, Integer httpStatus) {}
}
