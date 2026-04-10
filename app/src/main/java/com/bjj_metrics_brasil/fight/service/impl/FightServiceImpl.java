package com.bjj_metrics_brasil.fight.service.impl;

import com.bjj_metrics_brasil.fight.model.request.CreateUserFightRequest;
import com.bjj_metrics_brasil.fight.model.response.ListAllUserFightsResponse;
import com.bjj_metrics_brasil.fight.repository.FightRepository;
import com.bjj_metrics_brasil.fight.repository.entity.Fight;
import com.bjj_metrics_brasil.fight.service.FightService;
import com.bjj_metrics_brasil.utils.ListAllUserFightsResponseBuilder;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class FightServiceImpl implements FightService {

    private final FightRepository fightRepository;
    private final ListAllUserFightsResponseBuilder listAllUserFightsResponseBuilder;

    @Override
    public void createUserFight(
        UUID athleteId,
        CreateUserFightRequest createUserFightRequest
    ) {
        log.info("Creating fight registry for athlete_id: {}", athleteId);
        fightRepository.save(
            Fight
                .builder()
                .athleteId(athleteId)
                .opponentName(createUserFightRequest.getOpponentName())
                .opponentBelt(createUserFightRequest.getOpponentBelt())
                .result(createUserFightRequest.getResult())
                .outcomeMethod(createUserFightRequest.getOutcomeMethod())
                .points(createUserFightRequest.getPoints())
                .eventName(createUserFightRequest.getEventName())
                .fightDurationInMinutes(
                    createUserFightRequest.getFightDurationInMinutes()
                )
                .belt(createUserFightRequest.getCategory().getBelt())
                .weight(createUserFightRequest.getCategory().getWeight())
                .date(createUserFightRequest.getDate())
                .notes(createUserFightRequest.getNotes())
                .build()
        );
    }

    @Override
    public List<ListAllUserFightsResponse> listAllUserFight(UUID athleteId) {
        List<Fight> fights = fightRepository.findAllByAthleteId(athleteId);

        return fights
            .stream()
            .map(fight -> listAllUserFightsResponseBuilder.builder(athleteId, fight))
            .toList();
    }
}
