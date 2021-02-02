package com.exam.service;


import com.exam.connector.TranslationPlanetRepository;
import com.exam.connector.WeatherRepository;
import com.exam.connector.model.TranslationPlanet;
import com.exam.connector.model.Weather;
import com.exam.enums.WeatherType;
import com.exam.service.model.PositionPlanets;
import com.exam.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;
import java.util.*;
import java.util.stream.Collectors;

import static com.exam.utils.Constants.*;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final double[] km_expectet = {2000.0, 3000.0, 4000.0, 5000.0};

    private final TranslationPlanetRepository daoT;
    private final WeatherRepository daoW;
    private final Util util;


    public void createWeather() {

        createTranslationPlanets();

        final Map<String, List<TranslationPlanet>> map = new HashMap<>();

        final List<Weather> listWeather = new ArrayList<>();

        final double[] distanceSun = new double[1];

        List<TranslationPlanet> list = new ArrayList<>();

        daoT.findAll().forEach(list::add);

        util.createPlanets().parallelStream().forEach(planet -> {

            if (FERENGI.equalsIgnoreCase(planet.getName())) {
                distanceSun[0] = planet.getDistanceSunKm();
            }


            List<TranslationPlanet> translation = Optional.of(list)
                .filter(translationPlanets -> !translationPlanets.isEmpty())
                .orElse(Collections.emptyList())
                .parallelStream()
                .filter(translationPlanet -> planet.getId() == translationPlanet.getPlanet_id())
                .sorted(Comparator.comparing(TranslationPlanet::getDay))
                .collect(Collectors.toCollection(ArrayList::new));

            map.put(planet.getName(), translation);


        });

        List<TranslationPlanet> translationFerengi = map.get(FERENGI);
        List<TranslationPlanet> translationVulcano = map.get(VULCANO);
        List<TranslationPlanet> translationBetasoide = map.get(BETASOIDE);

        translationFerengi
            .forEach(translationPlanet -> {

                TranslationPlanet vulcano = translationVulcano.stream()
                    .filter(translationPlanet1 -> translationPlanet.getDay() == translationPlanet1.getDay())
                    .findFirst()
                    .orElse(new TranslationPlanet());


                TranslationPlanet betasoide = translationBetasoide.stream()
                    .filter(translationPlanet1 -> vulcano.getDay() == translationPlanet1.getDay())
                    .findFirst()
                    .orElse(new TranslationPlanet());


                final double distanceFV = Point2D.distance(translationPlanet.getValue_x(), translationPlanet.getValue_y(), vulcano.getValue_x(), vulcano.getValue_y());

                final double distanceVB = Point2D.distance(vulcano.getValue_x(), vulcano.getValue_y(), betasoide.getValue_x(), betasoide.getValue_y());

                final double distanceBF = Point2D.distance(betasoide.getValue_x(), betasoide.getValue_y(), translationPlanet.getValue_x(), translationPlanet.getValue_y());


                final double product = distanceFV + distanceVB + distanceSun[0];

                final String[] weather = {WeatherType.UNDEFINED.toString()};

                Arrays.stream(km_expectet)
                    .filter(value -> product == value)
                    .findFirst()
                    .ifPresent(value -> weather[0] = WeatherType.DROUGHT.toString());


                final PositionPlanets positionPlanets = new PositionPlanets()
                    .setX1(translationPlanet.getValue_x())
                    .setX2(vulcano.getValue_x())
                    .setX3(betasoide.getValue_x())
                    .setY1(translationPlanet.getValue_y())
                    .setY2(vulcano.getValue_y())
                    .setY3(betasoide.getValue_y());

                Optional.of(calculateOptimalClimate(positionPlanets))
                    .filter(aBoolean -> aBoolean.equals(Boolean.TRUE))
                    .ifPresent(aBoolean -> weather[0] = WeatherType.SUNNY.toString());


                listWeather.add(new Weather()
                    .setDay(translationPlanet.getDay())
                    .setDistanceSF(distanceSun[0])
                    .setDistanceFV(distanceFV)
                    .setDistanceVB(distanceVB)
                    .setX1(translationPlanet.getValue_x())
                    .setX2(vulcano.getValue_x())
                    .setX3(betasoide.getValue_x())
                    .setY1(translationPlanet.getValue_y())
                    .setY2(vulcano.getValue_y())
                    .setY3(betasoide.getValue_y())
                    .setSum(product)
                    .setWeather(weather[0])
                );

            });


        daoW.saveAll(listWeather);


    }


    private boolean calculateOptimalClimate(final PositionPlanets positionPlanets) {

        final double operation1 = (positionPlanets.getX2() - positionPlanets.getX1()) / (positionPlanets.getX3() - positionPlanets.getX2());

        final double operation2 = (positionPlanets.getY2() - positionPlanets.getY1()) / (positionPlanets.getY3() - positionPlanets.getY2());

        return operation1 == operation2;

    }


    private void createTranslationPlanets() {

        final int[] degrees = {0};

        util.createPlanets().forEach(planet -> {

            final List<TranslationPlanet> listTranslationPlanet = new ArrayList<>();

            int planetId = planet.getId();
            int velocity = planet.getVelocity();
            int distanceSun = planet.getDistanceSunKm();

            degrees[0] = planet.getAngleInDegrees();


            for (int i = 1; i <= CALCULATE_DAYS; i++) {

                if (velocity < 0) {
                    degrees[0] = degrees[0] + velocity;
                } else {
                    degrees[0] = degrees[0] + velocity;
                }


                final double radianAngle = util.convertInRadians(degrees[0]);

                final Point2D point = util.createPosition(radianAngle, distanceSun);


                listTranslationPlanet.add(
                    new TranslationPlanet()
                        .setDay(i)
                        .setDegrees(degrees[0])
                        .setRadian_angles(radianAngle)
                        .setValue_x(point.getX())
                        .setValue_y(point.getY())
                        .setPlanet_id(planetId)
                );


            }

            daoT.saveAll(listTranslationPlanet);

        });
    }


    /*private double getDistance(final double x1, final double x2, final double y1, final double y2) {

        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }*/


}
