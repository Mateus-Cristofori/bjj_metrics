package com.bjj_metrics_brasil.fight.model.request;

import com.bjj_metrics_brasil.athlete.model.Enum.BeltEnum;
import com.bjj_metrics_brasil.fight.model.Enum.FightOutcomeMethodEnum;
import com.bjj_metrics_brasil.fight.model.Enum.FightResultEnum;
import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserFightRequest {

    @NotNull(message = "Fight result must be provided")
    private FightResultEnum result;

    private FightOutcomeMethodEnum outcomeMethod;

    private String opponentName;

    @NotNull(message = "The opponent belt must be provided")
    private BeltEnum opponentBelt;

    private Integer points;

    private String eventName;

    @NotNull(message = "Your category (weight and belt level) needs to be specified")
    private Category category;

    @NotNull(message = "The duration of the fight must be provided")
    private Integer fightDurationInMinutes;

    @NotNull(message = "Fight date must be provided")
    private LocalDate date;

    private String notes;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Category {

        private BeltEnum belt;
        private Double weight;
    }
}
