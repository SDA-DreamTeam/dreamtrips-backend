package com.github.dreamteam.service.country;

import com.github.dreamteam.model.Country;
import com.github.dreamteam.pojo.AddCountryRequest;

import java.util.List;


public interface CountryService {

    Country getCountryById(long id);

    List<Country> getAllCountries();

    Country save(AddCountryRequest request);
}
