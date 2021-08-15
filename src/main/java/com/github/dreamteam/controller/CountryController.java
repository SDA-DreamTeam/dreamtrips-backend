package com.github.dreamteam.controller;

import com.github.dreamteam.model.Country;
import com.github.dreamteam.pojo.AddCountryRequest;
import com.github.dreamteam.service.country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @ResponseBody
    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    @ResponseBody
    @GetMapping("/{idCode}")
    public Country getCountryById(@PathVariable("idCode") long idCode) {
        return countryService.getCountryById(idCode);
    }

    @ResponseBody
    @PostMapping(value =  "", consumes = "application/json")
    public Country save(@RequestBody @Valid AddCountryRequest request) {
        return countryService.save(request);
    }
}
