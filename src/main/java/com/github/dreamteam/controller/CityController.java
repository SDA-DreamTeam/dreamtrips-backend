package com.github.dreamteam.controller;

import com.github.dreamteam.model.City;
import com.github.dreamteam.model.Country;
import com.github.dreamteam.repository.CityRepository;
import com.github.dreamteam.service.City.CityService;
import com.github.dreamteam.service.Country.CountryService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public @ResponseBody
    List<City> getAllCities(){
        return cityService.getAllCities();
    }

    @GetMapping("/{idCode}")
    public @ResponseBody City getCityById(@PathVariable("idCode") long idCode) throws ObjectNotFoundException {
        return cityService.getCityById(idCode);
    }
}
