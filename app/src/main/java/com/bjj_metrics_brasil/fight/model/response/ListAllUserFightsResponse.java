package com.bjj_metrics_brasil.fight.model.response;

import com.bjj_metrics_brasil.athlete.model.Enum.BeltEnum;
import com.bjj_metrics_brasil.fight.model.Enum.FightOutcomeMethodEnum;
import com.bjj_metrics_brasil.fight.model.Enum.FightResultEnum;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListAllUserFightsResponse {

    private UUID athleteId;
    private FightResultEnum result;
    private FightOutcomeMethodEnum outcomeMethod;
    private String opponentName;
    private BeltEnum opponentBelt;
    private Integer points;
    private String eventName;
    private Category category;
    private Integer fightDurationInMinutes;
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
