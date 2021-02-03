package com.exam.connector;

import com.exam.connector.model.TranslationPlanet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TranslationPlanetRepository extends CrudRepository<TranslationPlanet, Long> {

    List<TranslationPlanet> findAllByDay(final long day);

    List<TranslationPlanet> findAllByDayBetween(final long day1, final long day2);

}
