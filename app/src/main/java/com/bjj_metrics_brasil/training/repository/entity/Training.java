package com.bjj_metrics_brasil.training.repository.entity;

import com.bjj_metrics_brasil.training.model.Enum.TrainingTypeEnum;
import jakarta.persistence.Entity;
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
