package com.exam.api;

import com.exam.api.model.Forecast;
import com.exam.api.model.Weather;
import com.exam.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiTestController implements ApiTestControllerInterface {

    private final WeatherService weatherService;

    @Override
    public Forecast getWeatherDays(final String days) {

        return weatherService.getForecastDay(days);

    }

    @Override
    public void createJob() {
        weatherService.createWeather();
    }

    @Override
    public Weather getWeatherDay(final String day) {

        return weatherService.getWeatherDay(day);
    }
}
