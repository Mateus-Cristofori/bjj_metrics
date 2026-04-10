package com.bjj_metrics_brasil.fight.service;

import com.bjj_metrics_brasil.fight.model.request.CreateUserFightRequest;
import com.bjj_metrics_brasil.fight.model.response.ListAllUserFightsResponse;
import java.util.List;
import java.util.UUID;

public interface FightService {
    void createUserFight(UUID athleteId, CreateUserFightRequest createUserFightRequest);
    List<ListAllUserFightsResponse> listAllUserFight(UUID athleteId);
}
