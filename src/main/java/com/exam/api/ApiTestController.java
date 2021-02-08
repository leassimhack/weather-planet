package com.exam.api;

import com.exam.api.model.Forecast;
import com.exam.api.model.WeatherResponse;
import com.exam.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class ApiTestController implements ApiTestControllerInterface {

    private final WeatherService weatherService;

    @Override
    public Forecast getWeatherDays(final String days) {

        if (Integer.parseInt(days) < 1) {
            throw new IllegalArgumentException("Day cannot be less than 1");
        }

        return weatherService.getForecastDay(days);

    }

    @Override
    public void createJob() {

        weatherService.createWeather();
    }

    @Override
    public WeatherResponse getWeatherDay(final String day) {

        if (Integer.parseInt(day) < 1) {
            throw new IllegalArgumentException("Day cannot be less than 1");
        }

        return weatherService.getWeatherDay(day);
    }
}
