package com.exam.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class Forecast {

    private Integer totalSunnyDays;

    private Integer totalRainyDays;

    private Integer totalDroughtDays;

    private List<Weather> forecasts;

}
