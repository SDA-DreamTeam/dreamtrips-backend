package com.github.dreamteam.service.city;

import com.github.dreamteam.model.City;
import com.github.dreamteam.pojo.AddCityRequest;
import javassist.tools.rmi.ObjectNotFoundException;

import java.util.List;

public interface CityService {
    City getCityById(long id) throws ObjectNotFoundException;

    List<City> getAllCities();

    City save(AddCityRequest request);
}
