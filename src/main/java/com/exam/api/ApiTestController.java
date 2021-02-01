package com.exam.api;

import com.exam.api.model.Planet;
import com.exam.api.model.RequestPlanets;
import com.exam.service.PlanetsService;
import com.exam.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ApiTestController implements ApiTestControllerInterface {

    private final PlanetsService planetsService;

    private final TranslationService transalationService;

    @Override
    public Planet createPlanets(final RequestPlanets requestPlanets) {

        return planetsService.save(Optional.ofNullable(requestPlanets)
            .map(RequestPlanets::getPlanet).orElse(new Planet()));
    }

    @Override
    public void createPlanets(final Integer idPlanet) {

        transalationService.createTranslationValues(idPlanet);
    }


    @Override
    public List<Planet> getPlanets() {

        return planetsService.findAll();
    }
}
