package com.github.dreamteam.integration.misc;

import com.github.dreamteam.repository.AirportRepository;
import com.github.dreamteam.repository.CityRepository;
import com.github.dreamteam.repository.CountryRepository;
import com.github.dreamteam.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCleaner {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private HotelRepository hotelRepository;


    public void clean() {
        airportRepository.deleteAll();
        hotelRepository.deleteAll();
        cityRepository.deleteAll();
        countryRepository.deleteAll();
    }
}
