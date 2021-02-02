package com.exam.utils;

import com.exam.service.model.Planet;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Component;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static com.exam.utils.Constants.*;

@Component
public class Util {

    public double convertInRadians(final Integer angleInDegrees) {

        return Math.toRadians(angleInDegrees);
    }


    public Point2D createPosition(final double angleInRadians, final int radiusInKm) {

        double xAxis = Precision.round(Math.cos(angleInRadians) * radiusInKm, 12);
        double yAxis = Precision.round(Math.sin(angleInRadians) * radiusInKm, 12);

        return new Point2D.Double(xAxis, yAxis);
    }

    public List<Planet> createPlanets() {

        List<Planet> planets = new ArrayList<>();

        planets.add(new Planet()
            .setId(1)
            .setName(FERENGI)
            .setAngleInDegrees(361)
            .setVelocity(-1)
            .setDistanceSunKm(DISTANCE_SUN_FE)
            .setAngleInRadians(this.convertInRadians(361))
            .setPosition(this.createPosition(this.convertInRadians(361), DISTANCE_SUN_FE))

        );


        planets.add(new Planet()
            .setId(2)
            .setName(VULCANO)
            .setAngleInDegrees(-5)
            .setVelocity(5)
            .setDistanceSunKm(DISTANCE_SUN_VU)
            .setAngleInRadians(this.convertInRadians(-5))
            .setPosition(this.createPosition(this.convertInRadians(-5), DISTANCE_SUN_VU))

        );


        planets.add(new Planet()
            .setId(3)
            .setName(BETASOIDE)
            .setAngleInDegrees(363)
            .setVelocity(-3)
            .setDistanceSunKm(DISTANCE_SUN_BE)
            .setAngleInRadians(this.convertInRadians(363))
            .setPosition(this.createPosition(this.convertInRadians(363), DISTANCE_SUN_BE))

        );

        return planets;

    }


}
