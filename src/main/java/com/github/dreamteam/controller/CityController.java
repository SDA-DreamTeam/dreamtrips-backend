package com.github.dreamteam.controller;

import com.github.dreamteam.model.City;
import com.github.dreamteam.pojo.AddCityRequest;
import com.github.dreamteam.service.city.CityService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    @ResponseBody
    public List<City> getAllCities(){
        return cityService.getAllCities();
    }

    @GetMapping("/{idCode}")
    public @ResponseBody City getCityById(@PathVariable("idCode") long idCode) throws ObjectNotFoundException {
        return cityService.getCityById(idCode);
    }

    @PostMapping(value = "", consumes = "application/json")
    @ResponseBody
    public City save(@RequestBody @Valid AddCityRequest request) {
        return cityService.save(request);
    }
}
