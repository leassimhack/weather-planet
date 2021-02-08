package com.exam.service;

import com.exam.api.model.Forecast;
import com.exam.api.model.WeatherResponse;
import com.exam.connector.TranslationPlanetRepository;
import com.exam.connector.ValidationRepository;
import com.exam.connector.WeatherRepository;
import com.exam.connector.model.TranslationPlanet;
import com.exam.connector.model.ValidationJob;
import com.exam.enums.WeatherType;
import com.exam.utils.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.IterableResult;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.exam.factory.ApiFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @Mock
    private TranslationPlanetRepository daoT;
    @Mock
    private WeatherRepository daoW;
    @Mock
    private Util util;
    @Mock
    private ValidationRepository daoV;

    @MockBean
    private WeatherService weatherService;

    @BeforeEach
    public void initObjects() {


        weatherService = new WeatherService(daoT, daoW, util, daoV);
    }

    @Test
    public void when_create_job_weather_IsDrought_responseOk() {

        final List<ValidationJob> validationJob = new ArrayList<>();
        validationJob.add(new ValidationJob().setId(1).setFlag(Boolean.FALSE.toString()));


        when(daoV.findAll()).thenReturn(validationJob);
        when(util.createPlanets()).thenReturn(createPlanets());
        when(util.convertInRadians(anyInt())).thenReturn(0.000000000000001);
        when(util.createPosition(anyDouble(), anyInt())).thenReturn(new Point2D.Double(0.0, 0.0));
        when(daoT.findAll()).thenReturn(createListTranslationPlanetDrought());

        weatherService.createWeather();

    }

    @Test
    public void getForecastDay_whenAllIsOK_ExpectedResponseWeather() {

        when(util.createPlanets()).thenReturn(createPlanets());
        when(daoT.findAllByDayBetween(anyLong(), anyLong())).thenReturn(createListTranslationPlanetDrought());
        when(daoW.findAllByDayBetween(anyLong(), anyLong())).thenReturn(createListWeather());

        final Forecast forecast = weatherService.getForecastDay("2");


        assertNotNull(forecast);
        assertEquals(3, forecast.getWeathers().size());
        assertEquals(WeatherType.RAIN, forecast.getWeathers().get(0).getMain());
        assertEquals(1, forecast.getWeathers().get(0).getAllDays());
        assertEquals(WeatherType.DROUGHT, forecast.getWeathers().get(2).getMain());


    }


    @Test
    public void getWeatherDay_whenAllIsOK_ExpectedResponseWeather() {

        when(util.createPlanets()).thenReturn(createPlanets());
        when(daoT.findAllByDay(anyLong())).thenReturn(createListTranslationPlanetDrought());
        when(daoW.findAllByDay(anyLong())).thenReturn(createListWeather().get(0));

        final WeatherResponse weatherResponse = weatherService.getWeatherDay("1");
        assertNotNull(weatherResponse);


    }


}
