package com.github.dreamteam.integration;

import com.github.dreamteam.integration.misc.DatabaseCleaner;
import com.github.dreamteam.model.Airport;
import com.github.dreamteam.model.City;
import com.github.dreamteam.model.Country;
import com.github.dreamteam.model.Hotel;
import com.github.dreamteam.repository.AirportRepository;
import com.github.dreamteam.repository.CityRepository;
import com.github.dreamteam.repository.CountryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AirportIntegrationTest extends AbstractTest{


    @Test
    public void get_airport_by_id() throws Exception {
        // given
        Country randomCountry = addCountryActionProvider.getObject().execute();

        City randomCity = addCityActionProvider.getObject().setCountry(randomCountry).execute();

        Airport randomAirport = addAirportActionProvider.getObject().setCity(randomCity).execute();

        // when
        ResultActions resultActions = mvc.perform(
                get("/airports/" + randomAirport.getId())
        );

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(randomAirport.getId()), Long.class))
                .andExpect(jsonPath("name", is(randomAirport.getName())))
                .andExpect(jsonPath("city.id", is(randomCity.getId()), Long.class))
                .andExpect(jsonPath("city.name", is(randomCity.getName())))
                .andExpect(jsonPath("city.country.id", is(randomCountry.getId()), Long.class))
                .andExpect(jsonPath("city.country.name", is(randomCountry.getName())));
    }
}
