package com.bjj_metrics_brasil.client;

import com.bjj_metrics_brasil.onboarding.model.request.CreateAthleteRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "app-client", url = "${app-client.feignUrl}")
public interface AppClient {
    @PostMapping("/api/v1/athlete/create")
    void createAthlete(@RequestBody CreateAthleteRequest createAthleteRequest);
}
