package com.exam.api;

import com.exam.api.model.Weather;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/api/")
public interface ApiTestControllerInterface {

    @RequestMapping(value = "/weather", method = RequestMethod.POST)
    void createPlanets();

    @RequestMapping(value = "/weather/{day}", method = RequestMethod.POST)
    Weather createPlanets(@PathVariable final String day);


}
