package com.exam.utils;

import com.exam.service.model.Planet;
import com.exam.service.model.PositionPlanets;
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

        double x = Precision.round(Math.cos(angleInRadians) * radiusInKm, 12);
        double y = Precision.round(Math.sin(angleInRadians) * radiusInKm, 12);

        return new Point2D.Double(x, y);
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


    public boolean isTriangle(double[][] coordinates) {

        return ((coordinates[0][0] * coordinates[1][1] * coordinates[2][2]) +
                (coordinates[0][1] * coordinates[1][2] * coordinates[2][0]) +
                (coordinates[1][0] * coordinates[2][1] * coordinates[0][2]) -
                (coordinates[0][2] * coordinates[1][1] * coordinates[2][0]) -
                (coordinates[0][1] * coordinates[1][0] * coordinates[2][2]) -
                (coordinates[1][2] * coordinates[2][1] * coordinates[0][0])) != 0;


    }


    public boolean isPointInTriangle(final PositionPlanets positionPlanets) {

        final Point2D d = new Point2D.Double(
            positionPlanets.getVulcano().getX() - (positionPlanets.getFerengi().getX()),
            positionPlanets.getVulcano().getY() - (positionPlanets.getFerengi().getY())
        );

        final Point2D e = new Point2D.Double(
            positionPlanets.getBetasoide().getX() - (positionPlanets.getFerengi().getX()),
            positionPlanets.getBetasoide().getY() - (positionPlanets.getFerengi().getY())
        );

        final double w1 = (e.getX() * (positionPlanets.getFerengi().getY() - POSITION_ZERO) +
                           e.getY() * (POSITION_ZERO - positionPlanets.getFerengi().getX())) /
                          (d.getX() * e.getY() - d.getY() * e.getX());

        final double w2 = (0.0 - positionPlanets.getFerengi().getY() - w1 * d.getY()) / e.getY();

        return w1 >= POSITION_ZERO && (w2 > POSITION_ZERO) && ((w1 + w2) <= VALUE_ONE);

    }

}
