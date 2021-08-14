package com.github.dreamteam.integration.action;

import com.github.dreamteam.model.Country;
import com.github.dreamteam.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class AddCountryAction {

    @Autowired
    private CountryRepository countryRepository;

    private String name = "Estonia";

    public AddCountryAction setName(String name) {
        this.name = name;
        return this;
    }

    public Country execute() {
        Country country = new Country();
        country.setName(name);
        return countryRepository.save(country);
    }
}
