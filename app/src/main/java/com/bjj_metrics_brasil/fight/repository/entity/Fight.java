package com.bjj_metrics_brasil.fight.repository.entity;

import com.bjj_metrics_brasil.athlete.model.Enum.BeltEnum;
import com.bjj_metrics_brasil.fight.model.Enum.FightOutcomeMethodEnum;
import com.bjj_metrics_brasil.fight.model.Enum.FightResultEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fight")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fight {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    private UUID athleteId;

    private String opponentName;

    @Enumerated(EnumType.STRING)
    private BeltEnum opponentBelt;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FightResultEnum result;

    @Enumerated(EnumType.STRING)
    private FightOutcomeMethodEnum outcomeMethod;

    private Integer points;

    private String eventName;

    private double weight;

    private BeltEnum belt;

    private Integer fightDurationInMinutes;

    private LocalDate date;

    private String notes;
}
