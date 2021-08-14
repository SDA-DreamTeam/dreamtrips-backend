package com.github.dreamteam.service.hotel;

import com.github.dreamteam.model.Hotel;
import javassist.tools.rmi.ObjectNotFoundException;

import java.util.List;


public interface HotelService {
    Hotel getHotelById(long id) throws ObjectNotFoundException;

    List<Hotel> getAllHotels();
}
