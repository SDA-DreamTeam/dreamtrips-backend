package com.github.dreamteam.integration;

import com.github.dreamteam.model.City;
import com.github.dreamteam.model.Country;
import com.github.dreamteam.model.Hotel;
import com.github.dreamteam.repository.HotelRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class HotelIntegrationTest extends AbstractTest {


    @Test
    public void get_hotel_by_id() throws Exception {
        // given
        Country randomCountry = addCountryActionProvider.getObject().execute();

        City randomCity = addCityActionProvider.getObject().setCountry(randomCountry).execute();

        Hotel randomHotel = addHotelActionProvider.getObject().setCity(randomCity).execute();

        // when
        ResultActions resultActions = mvc.perform(
                get("/hotels/" + randomHotel.getId())
        );

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(randomHotel.getId()), Long.class))
                .andExpect(jsonPath("name", is(randomHotel.getName())))
                .andExpect(jsonPath("city.id", is(randomCity.getId()), Long.class))
                .andExpect(jsonPath("city.name", is(randomCity.getName())))
                .andExpect(jsonPath("city.country.id", is(randomCountry.getId()), Long.class))
                .andExpect(jsonPath("city.country.name", is(randomCountry.getName())));
    }

}
