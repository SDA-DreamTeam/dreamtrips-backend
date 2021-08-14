package com.github.dreamteam.service.country;

import com.github.dreamteam.model.Country;
import javassist.tools.rmi.ObjectNotFoundException;

import java.util.List;



public interface CountryService {

    Country getCountryById(long id) throws ObjectNotFoundException;

    List<Country> getAllCountries();

}
