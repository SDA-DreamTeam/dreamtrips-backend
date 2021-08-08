package com.github.dreamteam.controller;

import com.github.dreamteam.model.City;
import com.github.dreamteam.repository.CityRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {

    private final CityRepository cityRepository;

    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @RequestMapping(value = "/city")
    public List<City> findAll() {
        return cityRepository.findAll();
    }
}
