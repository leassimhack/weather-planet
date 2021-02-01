package com.exam.service;


import com.exam.connector.PlanetRepository;
import com.exam.connector.model.Planet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanetsService {

    private final PlanetRepository dao;

    public com.exam.api.model.Planet save(final com.exam.api.model.Planet planet) {

        return createPlanetResponse(
            dao.save(
                createPlanet(planet)));


    }

    public List<com.exam.api.model.Planet> findAll() {

        List<com.exam.api.model.Planet> planets = new ArrayList<>();

        dao.findAll().forEach(planet -> planets.add(createPlanetResponse(planet)));

        return planets;


    }


    private Planet createPlanet(final com.exam.api.model.Planet planet) {

        return new Planet()
            .setName(planet.getName())
            .setRadiusKM(Double.parseDouble(planet.getRadiusKM().toString()))
            .setDistanceSunKm(Double.parseDouble(planet.getDistanceSunKm().toString()))
            .setVelocity(planet.getVelocityInDegreesPerDay());


    }

    private com.exam.api.model.Planet createPlanetResponse(final Planet planet) {


        return new com.exam.api.model.Planet()
            .setId((int) planet.getId())
            .setName(planet.getName())
            .setRadiusKM(BigDecimal.valueOf(planet.getRadiusKM()))
            .setDistanceSunKm(BigDecimal.valueOf(planet.getDistanceSunKm()))
            .setVelocityInDegreesPerDay(planet.getVelocity());


    }


}
