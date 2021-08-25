package com.github.dreamteam.service.airport;

import com.github.dreamteam.exception.NotFoundException;
import com.github.dreamteam.model.Airport;
import com.github.dreamteam.model.City;
import com.github.dreamteam.pojo.AddAirportRequest;
import com.github.dreamteam.repository.AirportRepository;
import com.github.dreamteam.repository.CityRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private CityRepository cityRepository;

    @Override
    public Airport getAirportById(long airportId) throws ObjectNotFoundException {
        return airportRepository.findById(airportId).orElseThrow(()->new ObjectNotFoundException("Unable to find such airport "+airportId));
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public Airport save(AddAirportRequest request) {
        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new NotFoundException("City not found"));
        Airport airport=new Airport();
        airport.setName(request.getName());
        airport.setCity(city);
        return airportRepository.save(airport);
    }
}
