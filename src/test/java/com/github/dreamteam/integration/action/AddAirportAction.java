package com.github.dreamteam.integration.action;

import com.github.dreamteam.model.Airport;
import com.github.dreamteam.model.City;
import com.github.dreamteam.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class AddAirportAction {

    @Autowired
    private AirportRepository airportRepository;

    private String name = "Lennart Meri Tallinna Lennujaam";
    private City city;

    public AddAirportAction setName(String name) {
        this.name = name;
        return this;
    }

    public AddAirportAction setCity(City city) {
        this.city = city;
        return this;
    }

    public Airport execute() {
        Airport airport = new Airport();
        airport.setCity(Objects.requireNonNull(city));
        airport.setName(name);
        return airportRepository.save(airport);
    }
}
