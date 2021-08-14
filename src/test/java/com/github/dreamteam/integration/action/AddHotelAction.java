package com.github.dreamteam.integration.action;

import com.github.dreamteam.model.City;
import com.github.dreamteam.model.Country;
import com.github.dreamteam.model.Hotel;
import com.github.dreamteam.repository.CityRepository;
import com.github.dreamteam.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class AddHotelAction {
    @Autowired
    private HotelRepository hotelRepository;

    private String name = "Hilton";
    private City city;
    private String description="5* hotel";


    public AddHotelAction setName(String name) {
        this.name = name;
        return this;
    }

    public AddHotelAction setCity(City city) {
        this.city = city;
        return this;
    }
    public Hotel execute() {
        Hotel hotel=new Hotel();
        hotel.setCity(Objects.requireNonNull(city));
        hotel.setName(name);
        hotel.setDescription(description);
        return hotelRepository.save(hotel);
    }

}
