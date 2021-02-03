package com.exam.api;

import com.exam.api.model.Forecast;
import com.exam.api.model.Weather;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/api/")
public interface ApiTestControllerInterface {

    @RequestMapping(value = "/forecast/{days}", method = RequestMethod.GET)
    Forecast getWeatherDays(@PathVariable final String days);

    @RequestMapping(value = "/weather", method = RequestMethod.POST)
    void createJob();

    @RequestMapping(value = "/weather/{day}", method = RequestMethod.GET)
    Weather getWeatherDay(@PathVariable final String day);


}
