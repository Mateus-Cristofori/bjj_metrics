package com.bjj_metrics_brasil.bjj.metrics.training.repository.entity;

import com.bjj_metrics_brasil.bjj.metrics.training.model.Enum.TrainingTypeEnum;
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
@Table(name = "training")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Training {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    private UUID athleteId;

    @NotNull
    private TrainingTypeEnum trainingType;

    @NotNull
    private Integer durationMinutes;
    private String notes;
    private LocalDate date;
}
