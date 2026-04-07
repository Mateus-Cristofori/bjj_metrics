package com.bjj_metrics_brasil.authentication.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @NotNull(message = "The email field cannot be empty")
    private String email;

    @NotNull(message = "The password field cannot be empty")
    private String password;
}
