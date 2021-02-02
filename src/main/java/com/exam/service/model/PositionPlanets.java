package com.exam.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PositionPlanets {

    private double x1;
    private double x2;
    private double x3;
    private double y1;
    private double y2;
    private double y3;
}
