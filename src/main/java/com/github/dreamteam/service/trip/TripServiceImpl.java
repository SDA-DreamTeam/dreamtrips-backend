package com.github.dreamteam.service.trip;

import com.github.dreamteam.exception.NotFoundException;
import com.github.dreamteam.model.Hotel;
import com.github.dreamteam.model.Trip;
import com.github.dreamteam.repository.HotelRepository;
import com.github.dreamteam.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService{
    @Autowired
    private TripRepository tripRepository;

    @Override
    public Trip getTripById(long tripId) {
        return tripRepository.findById(tripId)
                .orElseThrow(() -> new NotFoundException("Unable to find such trip " + tripId));
    }

    @Override
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }
}
