package com.github.dreamteam.service.trip;

import com.github.dreamteam.model.Hotel;
import com.github.dreamteam.model.Trip;
import javassist.tools.rmi.ObjectNotFoundException;

import java.util.List;

public interface TripService {
    Trip getTripById(long id) throws ObjectNotFoundException;

    List<Trip> getAllTrips();
}
