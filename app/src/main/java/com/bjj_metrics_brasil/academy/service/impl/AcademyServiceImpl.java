package com.bjj_metrics_brasil.academy.service.impl;

import com.bjj_metrics_brasil.academy.model.request.CreateAcademyRequest;
import com.bjj_metrics_brasil.academy.repository.AcademyRepository;
import com.bjj_metrics_brasil.academy.repository.entity.Academy;
import com.bjj_metrics_brasil.academy.service.AcademyService;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AcademyServiceImpl implements AcademyService {

    private final AcademyRepository academyRepository;

    @Override
    public void createAcademy(CreateAcademyRequest createAcademyRequest) {
        log.info("Registering academy for userId: {}", createAcademyRequest.getUserId());
        academyRepository.save(
            Academy
                .builder()
                .userId(createAcademyRequest.getUserId())
                .academyName(createAcademyRequest.getAcademyName())
                .city(createAcademyRequest.getCity())
                .state(createAcademyRequest.getState())
                .country(createAcademyRequest.getCountry())
                .createdAt(LocalDateTime.now())
                .build()
        );
    }
}
