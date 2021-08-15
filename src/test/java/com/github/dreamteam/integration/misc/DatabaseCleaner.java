package com.github.dreamteam.integration.misc;

import com.github.dreamteam.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.SessionRepository;
import org.springframework.session.jdbc.JdbcIndexedSessionRepository;
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

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserRepository userRepository;

    public void clean() {
        userRepository.deleteAll();
        purchaseRepository.deleteAll();
        tripRepository.deleteAll();
        airportRepository.deleteAll();
        hotelRepository.deleteAll();
        cityRepository.deleteAll();
        countryRepository.deleteAll();
    }
}
