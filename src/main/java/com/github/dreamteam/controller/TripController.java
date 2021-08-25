package com.github.dreamteam.controller;

import com.github.dreamteam.model.Airport;
import com.github.dreamteam.model.Hotel;
import com.github.dreamteam.model.Trip;
import com.github.dreamteam.pojo.AddAirportRequest;
import com.github.dreamteam.pojo.AddTripRequest;
import com.github.dreamteam.pojo.FindTripRequest;
import com.github.dreamteam.service.hotel.HotelService;
import com.github.dreamteam.service.trip.TripServiceImpl;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {
    @Autowired
    private TripServiceImpl tripService;

    @GetMapping
    @ResponseBody
    public Page<Trip> getAllTrips(FindTripRequest request) {
        return tripService.getAllTrips(request);
    }

    @GetMapping("/{idCode}")
    public @ResponseBody
    Trip getTripById(@PathVariable("idCode") long idCode) throws ObjectNotFoundException {
        return tripService.getTripById(idCode);
    }

    @PostMapping(value = "", consumes = "application/json")
    @ResponseBody
    public Trip save(@RequestBody @Valid AddTripRequest request) {
        return tripService.save(request);
    }
}
