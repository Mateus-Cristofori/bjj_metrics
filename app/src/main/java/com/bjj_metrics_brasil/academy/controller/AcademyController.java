package com.bjj_metrics_brasil.academy.controller;

import com.bjj_metrics_brasil.academy.model.request.CreateAcademyRequest;
import com.bjj_metrics_brasil.academy.service.AcademyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/academy")
@AllArgsConstructor
public class AcademyController {

    private final AcademyService academyService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAcademy(
        @RequestBody @Valid CreateAcademyRequest createAcademyRequest
    ) {
        academyService.createAcademy(createAcademyRequest);
    }
}
