package com.bjj_metrics_brasil.annotation.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticatedUser {

    private UUID userId;
    private UUID athleteId;
}
