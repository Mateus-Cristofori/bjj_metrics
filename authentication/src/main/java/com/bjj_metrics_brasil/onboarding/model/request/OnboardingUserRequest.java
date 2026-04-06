package com.bjj_metrics_brasil.onboarding.model.request;

import com.bjj_metrics_brasil.onboarding.model.Enum.BeltEnum;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

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

    private LocalDate birthDate;

    private Academy academy;

    @Getter
    @Builder
    public static class Academy {

        @NotNull(message = "Field academy name cannot be empty")
        private String academyName;

        @NotNull(message = "Field city cannot be empty")
        private String city;

        @NotNull(message = "Field state cannot be empty")
        private String state;

        @NotNull(message = "Field country cannot be empty")
        private String country;
    }
}
