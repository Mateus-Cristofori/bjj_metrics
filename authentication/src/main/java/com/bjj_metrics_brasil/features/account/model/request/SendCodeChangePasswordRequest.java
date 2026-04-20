package com.bjj_metrics_brasil.features.account.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendCodeChangePasswordRequest {

    @NotNull(message = "The email field must no be empty")
    private String email;
}
