package com.github.dreamteam.controller;

import com.github.dreamteam.model.Airport;
import com.github.dreamteam.service.airport.AirportService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping
    public @ResponseBody List<Airport> getAllAirports(){
        return airportService.getAllAirports();
    }

    @GetMapping("/{idCode}")
    public @ResponseBody Airport getAirportById(@PathVariable("idCode") long idCode) throws ObjectNotFoundException {
        return airportService.getAirportById(idCode);
    }

}
