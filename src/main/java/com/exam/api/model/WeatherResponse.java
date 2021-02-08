package com.exam.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class WeatherResponse {

    private int day;

    private WeatherMain weather;

    private double precipitation;

    private List<Planet> planets;

}
