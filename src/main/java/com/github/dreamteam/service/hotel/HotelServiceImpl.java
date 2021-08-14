package com.github.dreamteam.service.hotel;

import com.github.dreamteam.exception.NotFoundException;
import com.github.dreamteam.model.Hotel;
import com.github.dreamteam.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel getHotelById(long hotelId) {
        return hotelRepository.findById(hotelId)
                .orElseThrow(() -> new NotFoundException("Unable to find such hotel " + hotelId));
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}
