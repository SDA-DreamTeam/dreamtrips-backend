package com.github.dreamteam.service.city;

import com.github.dreamteam.model.City;
import javassist.tools.rmi.ObjectNotFoundException;

import java.util.List;

public interface CityService {
    City getCityById(long id) throws ObjectNotFoundException;

    List<City> getAllCities();
}
