package com.exam.api.model;

import com.exam.enums.WeatherType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class Weather {

    private int day;

    private WeatherType weatherType;

    private double precipitation;

    private List<Planet> planets;

}
