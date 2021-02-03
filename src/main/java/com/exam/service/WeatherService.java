package com.exam.service;


import com.exam.api.model.Forecast;
import com.exam.api.model.Planet;
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

import static com.exam.enums.WeatherType.DROUGHT;
import static com.exam.enums.WeatherType.RAIN;
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
                    .ifPresent(value -> weather[0] = DROUGHT.toString());


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
                    .setY1(translationPlanet.getValue_y())
                    .setX2(vulcano.getValue_x())
                    .setY2(vulcano.getValue_y())
                    .setX3(betasoide.getValue_x())
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

                    if (degrees[0] <= 0) {
                        degrees[0] = ANGLE_360 + velocity;
                    } else {
                        degrees[0] = degrees[0] + velocity;
                    }

                } else {

                    if (degrees[0] >= ANGLE_360) {
                        degrees[0] = (degrees[0] - ANGLE_360) + velocity;
                    } else {
                        degrees[0] = degrees[0] + velocity;
                    }

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

    public com.exam.api.model.Weather getWeatherDay(final String day) {

        List<Planet> planets = new ArrayList<>();

        com.exam.api.model.Weather response = new com.exam.api.model.Weather();

        final List<TranslationPlanet> listTranslations = daoT.findAllByDay(new Long(day));
        final Weather weather = daoW.findAllByDay(new Long(day));

        util.createPlanets().forEach(planet -> {

            final TranslationPlanet translation = listTranslations.stream()
                .filter(translationPlanet -> planet.getId() == translationPlanet.getPlanet_id())
                .findFirst()
                .orElse(new TranslationPlanet());


            planets.add(new Planet()
                .setName(planet.getName())
                .setAngleInDegrees(translation.getDegrees())
                .setAngleInRadians(translation.getRadian_angles())
                .setVelocity(planet.getVelocity())
                .setPosition(new Point2D.Double(translation.getValue_x(), translation.getValue_y()))
                .setId(planet.getId()));


        });
        response.setPlanets(planets);
        response.setDay(Integer.parseInt(day));
        response.setWeatherType(WeatherType.valueOf(weather.getWeather()));
        return response;
    }


    public Forecast getForecastDay(final String days) {


        List<com.exam.service.model.Planet> planets = util.createPlanets();
        Forecast response = new Forecast();
        List<com.exam.api.model.Weather> forecast = new ArrayList<>();

        final List<TranslationPlanet> listTranslations = daoT.findAllByDayBetween(new Long("1"), new Long(days));
        final List<Weather> finalWeatherList = daoW.findAllByDayBetween(new Long("1"), new Long(days));


        int[] countD = {0};
        int[] countR = {0};
        int[] countS = {0};


        finalWeatherList.forEach(weather -> {

            if (DROUGHT.toString().equals(weather.getWeather())) {
                countD[0]++;
            }
            if (RAIN.toString().equals(weather.getWeather())) {
                countR[0]++;
            }
            if (countS.toString().equals(weather.getWeather())) {
                countS[0]++;
            }


            List<Planet> listPlanets = new ArrayList<>();
            com.exam.api.model.Weather wea = new com.exam.api.model.Weather();


            final List<TranslationPlanet> translations = listTranslations.stream()
                .filter(translationPlanet -> weather.getDay() == translationPlanet.getDay())
                .collect(Collectors.toCollection(ArrayList::new));


            translations.forEach(translationPlanet -> {

                com.exam.service.model.Planet planet = planets.stream()
                    .filter(planet1 -> planet1.getId() == translationPlanet.getPlanet_id())
                    .findFirst()
                    .orElse(new com.exam.service.model.Planet());

                listPlanets.add(new Planet()
                    .setName(planet.getName())
                    .setAngleInDegrees(translationPlanet.getDegrees())
                    .setAngleInRadians(translationPlanet.getRadian_angles())
                    .setVelocity(planet.getVelocity())
                    .setPosition(new Point2D.Double(translationPlanet.getValue_x(), translationPlanet.getValue_y()))
                    .setId(planet.getId()));

            });

            wea.setPlanets(listPlanets);
            wea.setDay((int) weather.getDay());
            wea.setWeatherType(WeatherType.valueOf(weather.getWeather()));

            forecast.add(wea);

        });

        response.setForecasts(forecast);
        response.setTotalDroughtDays(countD[0]);
        response.setTotalRainyDays(countR[0]);
        response.setTotalSunnyDays(countS[0]);


        return response;
    }

}
