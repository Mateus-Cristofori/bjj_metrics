package com.bjj_metrics_brasil.bjj.metrics.roll.repository.entity;

import com.bjj_metrics_brasil.bjj.metrics.roll.model.Enum.RollResultEnum;
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
import java.util.UUID;

@Entity
@Table(name = "roll")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Roll {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    private UUID trainingId;

    private String partnerName;

    @NotNull
    private RollResultEnum rollResult;

    private String submission;

    @NotNull
    private Integer durationMinutes;
}
