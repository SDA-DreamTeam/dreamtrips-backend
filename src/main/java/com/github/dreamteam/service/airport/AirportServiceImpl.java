package com.github.dreamteam.service.airport;

import com.github.dreamteam.model.Airport;
import com.github.dreamteam.repository.AirportRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public Airport getAirportById(long airportId) throws ObjectNotFoundException {
        return airportRepository.findById(airportId).orElseThrow(()->new ObjectNotFoundException("Unable to find such airport "+airportId));
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }
}
