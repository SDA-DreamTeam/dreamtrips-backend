package com.github.dreamteam.service.City;

import com.github.dreamteam.model.City;
import com.github.dreamteam.model.Country;
import javassist.tools.rmi.ObjectNotFoundException;

import java.util.List;

public interface CityService {
    City getCityById(long id) throws ObjectNotFoundException;

    List<City> getAllCities();
}
