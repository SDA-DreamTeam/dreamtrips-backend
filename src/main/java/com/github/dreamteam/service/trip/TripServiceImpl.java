package com.github.dreamteam.service.trip;

import com.github.dreamteam.exception.NotFoundException;
import com.github.dreamteam.model.Airport;
import com.github.dreamteam.model.BoardBasis;
import com.github.dreamteam.model.Hotel;
import com.github.dreamteam.model.Trip;
import com.github.dreamteam.pojo.AddTripRequest;
import com.github.dreamteam.pojo.FindTripRequest;
import com.github.dreamteam.repository.AirportRepository;
import com.github.dreamteam.repository.HotelRepository;
import com.github.dreamteam.repository.TripRepository;
import com.github.dreamteam.repository.TripRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TripServiceImpl implements TripService{
    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public Trip getTripById(long tripId) {
        return tripRepository.findById(tripId)
                .orElseThrow(() -> new NotFoundException("Unable to find such trip " + tripId));
    }

    @Override
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @Transactional
    public Trip save(AddTripRequest request) {
        Airport fromAirport = airportRepository.findById(request.getFromAirportId())
                .orElseThrow(() -> new NotFoundException("Airport not found"));
        Airport toAirport = airportRepository.findById(request.getToAirportId())
                .orElseThrow(() -> new NotFoundException("Airport not found"));
        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new NotFoundException("Hotel not found"));
        Trip trip = new Trip();
        trip.setId(request.getTripId());
        trip.setFromAirport(fromAirport);
        trip.setToAirport(toAirport);
        trip.setHotel(hotel);
        return tripRepository.save(trip);
    }
}
