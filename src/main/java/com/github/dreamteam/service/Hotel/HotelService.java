package com.github.dreamteam.service.Hotel;

import com.github.dreamteam.model.Country;
import com.github.dreamteam.model.Hotel;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HotelService {
    Hotel getHotelById(long id) throws ObjectNotFoundException;

    List<Hotel> getAllHotels();
}
