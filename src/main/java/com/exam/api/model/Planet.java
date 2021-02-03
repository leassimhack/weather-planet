package com.exam.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.awt.geom.Point2D;

@Getter
@Setter
@Accessors(chain = true)
public class Planet {

    private int id;
    private int velocity;
    private int angleInDegrees;
    private int distanceSunKm;
    private double angleInRadians;
    private String name;
    private Point2D position;

}
