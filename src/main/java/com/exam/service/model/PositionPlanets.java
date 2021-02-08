package com.exam.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.awt.geom.Point2D;

@Getter
@Setter
@Accessors(chain = true)
public class PositionPlanets {

    private Point2D ferengi;
    private Point2D vulcano;
    private Point2D betasoide;

}
