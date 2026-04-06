package com.bjj_metrics_brasil.onboarding.model.request;

import com.bjj_metrics_brasil.onboarding.model.Enum.BeltEnum;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OnboardingUserRequest {

    @NotNull(message = "The name field cannot be empty")
    private String name;

    @NotNull(message = "The lastname filed cannot be empty")
    private String lastname;

    @NotNull(message = "The email field cannot be empty")
    private String email;

    @NotNull(message = "The password field cannot be empty")
    private String password;

    @NotNull(message = "Your belt needs to be informed")
    private BeltEnum belt;

    @NotNull(message = "Your weight needs to be informed")
    private Double weight;

    private String academyName;
    private String city;
    private LocalDate birthDate;
}
