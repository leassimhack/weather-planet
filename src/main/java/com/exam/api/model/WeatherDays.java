package com.exam.api.model;

import com.exam.enums.WeatherType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class WeatherDays {

    private WeatherType main;
    @JsonProperty("all")
    private Integer allDays;
    @JsonProperty("max_day")
    private Integer max;
}
