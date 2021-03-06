package com.exam.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class Forecast {

    private  List<WeatherDays> weathers;

    private List<WeatherResponse> forecasts;

}
