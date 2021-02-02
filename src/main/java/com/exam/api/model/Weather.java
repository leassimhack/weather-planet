package com.exam.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class Weather {

    private Integer day;
    private String weatherType;
    private BigDecimal radiusKM;
    private BigDecimal distanceSunKm;
    private Integer velocityInDegreesPerDay;

}
