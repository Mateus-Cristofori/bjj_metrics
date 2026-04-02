package com.bjj_metrics_brasil.bjj.metrics.fight.repository.entity;

import com.bjj_metrics_brasil.bjj.metrics.fight.model.Enum.ResultFightEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.UUID;

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

    @NotNull
    private String eventName;

    @NotNull
    private ResultFightEnum result;

    private Integer pointsScored;
    private Integer pointsConceded;
    private LocalDate date;
}
