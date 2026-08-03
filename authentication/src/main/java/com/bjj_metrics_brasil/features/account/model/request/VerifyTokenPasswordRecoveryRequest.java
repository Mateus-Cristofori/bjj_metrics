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
public class VerifyTokenPasswordRecoveryRequest {

    @NotNull(message = "O email precisa ser informado.")
    private String email;

    @NotNull(message = "O código de recuperação precisa ser informado.")
    private String code;
}
