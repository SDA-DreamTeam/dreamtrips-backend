package com.github.dreamteam.service.airport;

import com.github.dreamteam.model.Airport;
import javassist.tools.rmi.ObjectNotFoundException;

import java.util.List;

public interface AirportService {
    Airport getAirportById(long id) throws ObjectNotFoundException;
    List<Airport> getAllAirports();
}
