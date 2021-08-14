package com.github.dreamteam.service.City;

import com.github.dreamteam.model.City;
import com.github.dreamteam.model.Country;
import com.github.dreamteam.repository.CityRepository;
import com.github.dreamteam.repository.CountryRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Override
    public City getCityById(long cityId) throws ObjectNotFoundException {
        return cityRepository.findById(cityId).orElseThrow(()->new ObjectNotFoundException("Unable to find such airport "+cityId));
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}
