package com.github.dreamteam.service.airport;

import com.github.dreamteam.model.Airport;
import com.github.dreamteam.pojo.AddAirportRequest;
import javassist.tools.rmi.ObjectNotFoundException;

import java.util.List;

public interface AirportService {
    Airport getAirportById(long id) throws ObjectNotFoundException;
    List<Airport> getAllAirports();
    Airport save(AddAirportRequest request);
}
