package com.exam.service;

import com.exam.connector.PlanetRepository;
import com.exam.connector.TranslationPlanetRepository;
import com.exam.connector.model.Planet;
import com.exam.connector.model.TraslationPlanet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TranslationService {

    private static final Double PI = 3.14159;
    private static final Integer ANGLE_180 = 180;
    private static final Integer ANGLE_360 = 360;
    private static final Integer ANGLE_0 = 0;
    private static final Integer CALCULATE_DAYS = 3065;

    private final PlanetRepository dao;
    private final TranslationPlanetRepository translationPlanetRepository;


    public void createTranslationValues(final Integer idPlanet) {

        int degrees = 0;

        final Planet planet = dao.findById(Long.valueOf(idPlanet))
            .orElse(new Planet());

        final double radius = planet.getDistanceSunKm();
        final Integer velocity = planet.getVelocity();

        if (velocity < 0) {
            degrees = 360;
        }

        for (int i = 1; i <= CALCULATE_DAYS; i++) {

            degrees = getDegrees(degrees, velocity);

            double radianAngles = getRadianAngles(degrees);

            translationPlanetRepository.save(new TraslationPlanet()
                .setDay(i)
                .setDegrees(degrees)
                .setRadian_angles(radianAngles)
                .setValue_x(radius * Math.cos(radianAngles))
                .setValue_y(radius * Math.sin(radianAngles))
                .setPlanet(planet)
            );
        }

    }

    private Integer getDegrees(final Integer degrees, final Integer velocity) {

        int degree;

        if (velocity > 0) {
            if (degrees >= ANGLE_360) {
                degree = degrees - ANGLE_360;
            } else {
                degree = degrees + velocity;
            }
        } else {

            if (degrees <= 0) {
                degree = ANGLE_360 + velocity;
            } else {
                degree = degrees + velocity;
            }

        }

        return degree;
    }

    private Double getRadianAngles(Integer degrees) {

        return degrees * (PI / ANGLE_180);
    }

}