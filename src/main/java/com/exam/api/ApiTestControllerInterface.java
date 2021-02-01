package com.exam.api;

import com.exam.api.model.Planet;
import com.exam.api.model.RequestPlanets;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/solar-system/")
public interface ApiTestControllerInterface {

    @RequestMapping(value = "/planet", method = RequestMethod.POST)
    Planet createPlanets(@RequestBody final RequestPlanets requestPlanets);


    @RequestMapping(value = "/planet/translation/{id-planet}", method = RequestMethod.POST)
    void createPlanets(@PathVariable("id-planet") final Integer idPlanet);


    @RequestMapping(value = "/planet", method = RequestMethod.GET)
    List<Planet> getPlanets();


}
