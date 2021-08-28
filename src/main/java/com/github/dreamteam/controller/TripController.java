package com.github.dreamteam.controller;

import com.github.dreamteam.model.Trip;
import com.github.dreamteam.pojo.AddTripRequest;
import com.github.dreamteam.pojo.FindTripRequest;
import com.github.dreamteam.service.trip.TripServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripServiceImpl tripService;

    @GetMapping
    public Page<Trip> getAllTrips(FindTripRequest request) {
        return tripService.getAllTrips(request);
    }

    @GetMapping("suggestions")
    public Page<Trip> suggestions() {
        return tripService.suggestions();
    }

    @GetMapping("/{id}")
    public Trip getTripById(@PathVariable long id) {
        return tripService.getTripById(id);
    }

    @PostMapping(value = "", consumes = "application/json")
    public Trip save(@RequestBody @Valid AddTripRequest request) {
        return tripService.save(request);
    }
}
