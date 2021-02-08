package com.exam.factory;

import com.exam.connector.model.TranslationPlanet;
import com.exam.service.model.Planet;
import com.exam.connector.model.Weather;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static com.exam.utils.Constants.*;

public class ApiFactory {


    public static List<Planet> createPlanets() {

        List<Planet> planets = new ArrayList<>();

        planets.add(new Planet()
            .setId(1)
            .setName(FERENGI)
            .setAngleInDegrees(361)
            .setVelocity(-1)
            .setDistanceSunKm(DISTANCE_SUN_FE)
            .setAngleInRadians(6.283185307179586)
            .setPosition(new Point2D.Double(500.0, 0.0))

        );


        planets.add(new Planet()
            .setId(2)
            .setName(VULCANO)
            .setAngleInDegrees(-5)
            .setVelocity(5)
            .setDistanceSunKm(DISTANCE_SUN_VU)
            .setAngleInRadians(0.0)
            .setPosition(new Point2D.Double(1000.0, 0.0))
        );


        planets.add(new Planet()
            .setId(3)
            .setName(BETASOIDE)
            .setAngleInDegrees(363)
            .setVelocity(-3)
            .setDistanceSunKm(DISTANCE_SUN_BE)
            .setAngleInRadians(6.283185307179586)
            .setPosition(new Point2D.Double(2000.0, 0.0))

        );

        return planets;

    }


    public static List<TranslationPlanet> createListTranslationPlanetDrought() {

        final List<TranslationPlanet> list = new ArrayList<>();

        list.add(new TranslationPlanet()
            .setId(1)
            .setDay(1)
            .setDegrees(300)
            .setPlanet_id(1)
            .setRadian_angles(6.283185307179586)
            .setValue_x(500)
            .setValue_y(0));

        list.add(new TranslationPlanet()
            .setId(2)
            .setDay(1)
            .setDegrees(0)
            .setPlanet_id(2)
            .setRadian_angles(0)
            .setValue_x(1000)
            .setValue_y(0));

        list.add(new TranslationPlanet()
            .setId(3)
            .setDay(1)
            .setDegrees(360)
            .setPlanet_id(3)
            .setRadian_angles(6.283185307179586)
            .setValue_x(2000)
            .setValue_y(0));

        list.add(new TranslationPlanet()
            .setId(4)
            .setDay(2)
            .setDegrees(336)
            .setPlanet_id(1)
            .setRadian_angles(5.8643062867009474)
            .setValue_x(456.7727288213)
            .setValue_y(-203.3683215379));

        list.add(new TranslationPlanet()
            .setId(5)
            .setDay(2)
            .setDegrees(120)
            .setPlanet_id(2)
            .setRadian_angles(2.0943951023931953)
            .setValue_x(-500)
            .setValue_y(866.025403784439));

        list.add(new TranslationPlanet()
            .setId(6)
            .setDay(2)
            .setDegrees(288)
            .setPlanet_id(3)
            .setRadian_angles(5.026548245743669)
            .setValue_x(700)
            .setValue_y(-1902.113032590307));


        list.add(new TranslationPlanet()
            .setId(7)
            .setDay(3)
            .setDegrees(0)
            .setPlanet_id(1)
            .setRadian_angles(5.8643062867009474)
            .setValue_x(100)
            .setValue_y(200));

        list.add(new TranslationPlanet()
            .setId(8)
            .setDay(3)
            .setDegrees(0)
            .setPlanet_id(2)
            .setRadian_angles(2.0943951023931953)
            .setValue_x(1000)
            .setValue_y(200));

        list.add(new TranslationPlanet()
            .setId(9)
            .setDay(3)
            .setDegrees(0)
            .setPlanet_id(3)
            .setRadian_angles(5.026548245743669)
            .setValue_x(2000)
            .setValue_y(200));


        return list;
    }


    public static double[][] createCoordinateMatrix() {

        final double[][] coordinateMatrix = new double[3][3];

        coordinateMatrix[0][0] = 456.7727288213;
        coordinateMatrix[0][1] = -203.3683215379;
        coordinateMatrix[0][2] = VALUE_ONE;

        coordinateMatrix[1][0] = -500;
        coordinateMatrix[1][1] = 866.025403784439;
        coordinateMatrix[1][2] = VALUE_ONE;

        coordinateMatrix[2][0] = 618.033988749894;
        coordinateMatrix[2][1] = -1902.113032590307;
        coordinateMatrix[2][2] = VALUE_ONE;

        return coordinateMatrix;

    }


    public static List<Weather> createListWeather() {

        final List<Weather> list = new ArrayList<>();

        list.add(new Weather()
            .setId(1)
            .setDay(1)
            .setPrecipitation(0)
            .setWeather("DROUGHT")
            .setX1(500)
            .setX2(1000)
            .setX3(2000)
            .setY1(0)
            .setY2(0)
            .setY3(0)
        );

        list.add(new Weather()
            .setId(2)
            .setDay(2)
            .setPrecipitation(6126.705493339671)
            .setWeather("RAIN")
            .setX1(456.7727288213)
            .setX2(-500)
            .setX3(618.033988749894)
            .setY1(-203.3683215379)
            .setY2(866.025403784439)
            .setY3(-1902.113032590307)
        );

        return list;
    }

}
