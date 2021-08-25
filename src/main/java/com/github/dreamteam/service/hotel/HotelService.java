package com.github.dreamteam.service.hotel;

import com.github.dreamteam.model.Airport;
import com.github.dreamteam.model.Hotel;
import com.github.dreamteam.pojo.AddAirportRequest;
import com.github.dreamteam.pojo.AddHotelRequest;
import javassist.tools.rmi.ObjectNotFoundException;

import java.util.List;


public interface HotelService {
    Hotel getHotelById(long id) throws ObjectNotFoundException;

    List<Hotel> getAllHotels();

    Hotel save(AddHotelRequest request);
}
