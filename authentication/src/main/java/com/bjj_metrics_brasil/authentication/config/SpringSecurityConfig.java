package com.bjj_metrics_brasil.authentication.config;

import com.bjj_metrics_brasil.authentication.config.token.service.JwtTokenFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    private JwtTokenFilterService jwtTokenFilterService;

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorize -> {
                //                configureAppRoutes(authorize);
                //                configureAuthenticationRoutes(authorize);
                authorize
                    .requestMatchers(
                        "/api/v1/onboarding",
                        "/api/v1/auth/login",
                        "/api/v1/athlete/create",
                        "/api/v1/academy/create",
                        "/api/v1/training/create",
                        "/api/v1/roll/create",
                        "/api/v1/roll/list-all",
                        "/api/v1/training/list-all",
                        "/api/v1/roll/list-all/*",
                        "/api/v1/fight/list-all",
                        "/api/v1/fight/create"
                    )
                    .permitAll();
            })
            .addFilterBefore(
                jwtTokenFilterService,
                UsernamePasswordAuthenticationFilter.class
            )
            .build();
    }
    //
    //    private void configureAppRoutes(
    //        AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry app
    //    ) {
    //        //        app.requestMatchers().hasAnyAuthority();
    //    }
    //
    //    private void configureAuthenticationRoutes(
    //        AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth
    //    ) {
    //        //        auth.requestMatchers().hasAnyAuthority();
    //    }
}
