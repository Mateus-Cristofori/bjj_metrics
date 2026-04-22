package com.bjj_metrics_brasil.utils.responseBuilder;

import com.bjj_metrics_brasil.fight.model.response.ListAllUserFightsResponse;
import com.bjj_metrics_brasil.fight.repository.entity.Fight;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class ListAllUserFightsResponseBuilder {

    public ListAllUserFightsResponse builder(UUID athleteId, Fight fight) {
        return ListAllUserFightsResponse
            .builder()
            .athleteId(athleteId)
            .opponentName(fight.getOpponentName())
            .opponentBelt(fight.getOpponentBelt())
            .result(fight.getResult())
            .outcomeMethod(fight.getOutcomeMethod())
            .points(fight.getPoints())
            .eventName(fight.getEventName())
            .category(buildCategory(fight))
            .fightDurationInMinutes(fight.getFightDurationInMinutes())
            .date(fight.getDate())
            .notes(fight.getNotes())
            .build();
    }

    private ListAllUserFightsResponse.Category buildCategory(Fight fight) {
        return ListAllUserFightsResponse.Category
            .builder()
            .belt(fight.getBelt())
            .weight(fight.getWeight())
            .build();
    }
}
