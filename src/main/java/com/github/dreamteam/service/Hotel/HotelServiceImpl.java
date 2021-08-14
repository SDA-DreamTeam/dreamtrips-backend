package com.github.dreamteam.service.Hotel;

import com.github.dreamteam.model.Country;
import com.github.dreamteam.model.Hotel;
import com.github.dreamteam.repository.CountryRepository;
import com.github.dreamteam.repository.HotelRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService{
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel getHotelById(long hotelId) throws ObjectNotFoundException {
        return hotelRepository.findById(hotelId).orElseThrow(()->new ObjectNotFoundException("Unable to find such airport "+hotelId));
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}
