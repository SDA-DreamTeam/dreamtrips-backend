package com.github.dreamteam.controller;

import com.github.dreamteam.model.Airport;
import com.github.dreamteam.model.Country;
import com.github.dreamteam.service.Airport.AirportService;
import com.github.dreamteam.service.Country.CountryService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping
    public @ResponseBody
    List<Country> getAllCountries(){
        return countryService.getAllCountries();
    }

    @GetMapping("/{idCode}")
    public @ResponseBody Country getCountryById(@PathVariable("idCode") long idCode) throws ObjectNotFoundException {
        return countryService.getCountryById(idCode);
    }
}
