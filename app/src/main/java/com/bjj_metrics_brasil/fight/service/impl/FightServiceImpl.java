package com.bjj_metrics_brasil.fight.service.impl;

import com.bjj_metrics_brasil.fight.model.request.CreateUserFightRequest;
import com.bjj_metrics_brasil.fight.model.response.ListAllUserFightsResponse;
import com.bjj_metrics_brasil.fight.repository.FightRepository;
import com.bjj_metrics_brasil.fight.repository.entity.Fight;
import com.bjj_metrics_brasil.fight.service.FightService;
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

    @Override
    public void createUserFight(
        UUID athleteId,
        CreateUserFightRequest createUserFightRequest
    ) {
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
                .weight(createUserFightRequest.getCategory().getWeight())
                .fightDurationInMinutes(
                    createUserFightRequest.getFightDurationInMinutes()
                )
                .belt(createUserFightRequest.getCategory().getBelt())
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
            .map(fight ->
                ListAllUserFightsResponse
                    .builder()
                    .athleteId(athleteId)
                    .opponentName(fight.getOpponentName())
                    .opponentBelt(fight.getOpponentBelt())
                    .result(fight.getResult())
                    .outcomeMethod(fight.getOutcomeMethod())
                    .points(fight.getPoints())
                    .eventName(fight.getEventName())
                    .weight(fight.getWeight())
                    .fightDurationInMinutes(fight.getFightDurationInMinutes())
                    .belt(fight.getBelt())
                    .date(fight.getDate())
                    .notes(fight.getNotes())
                    .build()
            )
            .toList();
    }
}
