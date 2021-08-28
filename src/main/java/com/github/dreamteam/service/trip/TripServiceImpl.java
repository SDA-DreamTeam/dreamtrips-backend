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
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TripServiceImpl {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripRepositoryCustom tripRepositoryCustom;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public Trip getTripById(long tripId) {
        return tripRepository.findById(tripId)
                .orElseThrow(() -> new NotFoundException("Unable to find such trip " + tripId));
    }

    public Page<Trip> getAllTrips(FindTripRequest request) {
        long fromAirportId = request.getFromAirportId();
        long toAirportId = request.getToAirportId();
        Long hotelId = request.getHotelId();
        LocalDate departureDate = request.getDepartureDate();
        Integer numberOfDays = request.getNumberOfDays();
        BoardBasis type = request.getType();
        BigDecimal priceAdult = request.getPriceAdult();
        BigDecimal priceChild = request.getPriceChild();
        Integer numberOfBedsAdult = request.getNumberOfBedsAdult();
        Integer numberOfBedsChild = request.getNumberOfBedsChild();
        return tripRepositoryCustom.findAll(fromAirportId, toAirportId, hotelId, departureDate, numberOfDays, type,
                priceAdult, priceChild, numberOfBedsAdult, numberOfBedsChild);
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

    public Page<Trip> suggestions() {
        return tripRepository.findAll(PageRequest.of(0, 10));
    }
}
