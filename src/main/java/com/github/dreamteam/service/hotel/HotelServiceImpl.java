package com.github.dreamteam.service.hotel;

import com.github.dreamteam.exception.NotFoundException;
import com.github.dreamteam.model.Airport;
import com.github.dreamteam.model.City;
import com.github.dreamteam.model.Hotel;
import com.github.dreamteam.pojo.AddHotelRequest;
import com.github.dreamteam.repository.CityRepository;
import com.github.dreamteam.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private CityRepository cityRepository;

    @Override
    public Hotel getHotelById(long hotelId) {
        return hotelRepository.findById(hotelId)
                .orElseThrow(() -> new NotFoundException("Unable to find such hotel " + hotelId));
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel save(AddHotelRequest request) {
        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new NotFoundException("City not found"));
        Hotel hotel= new Hotel();
        hotel.setName(request.getName());
        hotel.setCity(city);
        return hotelRepository.save(hotel);
    }
}
