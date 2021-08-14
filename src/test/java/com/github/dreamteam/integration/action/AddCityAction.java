package com.github.dreamteam.integration.action;

import com.github.dreamteam.model.City;
import com.github.dreamteam.model.Country;
import com.github.dreamteam.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class AddCityAction {

    @Autowired
    private CityRepository cityRepository;

    private String name = "Tallinn";
    private Country country;

    public AddCityAction setName(String name) {
        this.name = name;
        return this;
    }

    public AddCityAction setCountry(Country country) {
        this.country = country;
        return this;
    }

    public City execute() {
        City city = new City();
        city.setCountry(Objects.requireNonNull(country));
        city.setName(name);
        return cityRepository.save(city);
    }
}
