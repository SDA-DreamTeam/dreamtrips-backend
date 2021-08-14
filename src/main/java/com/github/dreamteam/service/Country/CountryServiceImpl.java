package com.github.dreamteam.service.Country;

import com.github.dreamteam.model.Airport;
import com.github.dreamteam.model.Country;
import com.github.dreamteam.repository.AirportRepository;
import com.github.dreamteam.repository.CountryRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country getCountryById(long countryId) throws ObjectNotFoundException {
        return countryRepository.findById(countryId).orElseThrow(()->new ObjectNotFoundException("Unable to find such airport "+countryId));
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
}

}
