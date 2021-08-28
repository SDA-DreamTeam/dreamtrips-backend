package com.github.dreamteam.service.country;

import com.github.dreamteam.exception.NotFoundException;
import com.github.dreamteam.model.Country;
import com.github.dreamteam.pojo.AddCountryRequest;
import com.github.dreamteam.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country getCountryById(long countryId) {
        return countryRepository.findById(countryId)
                .orElseThrow(() -> new NotFoundException("Unable to find such country " + countryId));
    }


    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country save(AddCountryRequest request) {

        Country country = new Country();
        country.setName(request.getName());
        return countryRepository.save(country);
    }

}
