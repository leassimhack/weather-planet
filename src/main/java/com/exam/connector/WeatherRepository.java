package com.exam.connector;

import com.exam.connector.model.Weather;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeatherRepository extends CrudRepository<Weather, Long> {

    Weather findAllByDay(final long day);

    List<Weather> findAllByDayBetween(final long day1, final long day2);

}
