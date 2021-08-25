package com.github.dreamteam.service.city;

import com.github.dreamteam.exception.NotFoundException;
import com.github.dreamteam.model.City;
import com.github.dreamteam.model.Country;
import com.github.dreamteam.pojo.AddCityRequest;
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

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public City getCityById(long cityId) throws ObjectNotFoundException {
        return cityRepository.findById(cityId).orElseThrow(()->new ObjectNotFoundException("Unable to find such city "+cityId));
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }


    @Override
    public City save(AddCityRequest request) {
        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new NotFoundException("Country not found"));
        City city = new City();
        city.setName(request.getName());
        city.setCountry(country);
        return cityRepository.save(city);
    }
}
