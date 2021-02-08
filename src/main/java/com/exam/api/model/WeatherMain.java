package com.exam.api.model;

import com.exam.enums.WeatherType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class WeatherMain {

    private WeatherType main;

}
