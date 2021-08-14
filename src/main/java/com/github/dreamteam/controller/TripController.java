package com.github.dreamteam.controller;

import com.github.dreamteam.model.Hotel;
import com.github.dreamteam.model.Trip;
import com.github.dreamteam.service.hotel.HotelService;
import com.github.dreamteam.service.trip.TripService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {
    @Autowired
    private TripService tripService;

    @GetMapping
    public @ResponseBody
    List<Trip> getAllTrips(){
        return tripService.getAllTrips();
    }

    @GetMapping("/{idCode}")
    public @ResponseBody Trip getTripById(@PathVariable("idCode") long idCode) throws ObjectNotFoundException {
        return tripService.getTripById(idCode);
    }
}
