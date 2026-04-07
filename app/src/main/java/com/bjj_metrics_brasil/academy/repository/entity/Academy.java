package com.bjj_metrics_brasil.academy.repository.entity;

import com.bjj_metrics_brasil.academy.model.Enum.CountryEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "academy")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Academy {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    private UUID userId;

    @NotNull
    private String academyName;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @Enumerated(EnumType.STRING)
    private CountryEnum country;

    private LocalDateTime createdAt;
}
