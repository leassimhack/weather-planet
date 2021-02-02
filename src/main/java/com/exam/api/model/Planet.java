package com.exam.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class Planet {

    private Integer id;
    private String name;
    private Integer velocityInDegreesPerDay;
    private BigDecimal radiusKM;
    private BigDecimal distanceSunKm;


}
