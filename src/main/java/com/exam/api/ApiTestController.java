package com.exam.api;

import com.exam.api.model.Weather;
import com.exam.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiTestController implements ApiTestControllerInterface {

    private final WeatherService weatherService;

    @Override
    public void createPlanets() {

        weatherService.createWeather();
    }

    @Override
    public Weather createPlanets(final String day) {

        return null;
    }

}
