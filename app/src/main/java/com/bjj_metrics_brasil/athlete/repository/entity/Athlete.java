package com.bjj_metrics_brasil.athlete.repository.entity;

import com.bjj_metrics_brasil.athlete.model.Enum.BeltEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "athlete")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Athlete {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    private UUID userId;

    @NotNull
    private String athleteName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BeltEnum belt;

    @NotNull
    private Double weight;

    private LocalDate birthDate;
    private LocalDateTime createdAt;
}
