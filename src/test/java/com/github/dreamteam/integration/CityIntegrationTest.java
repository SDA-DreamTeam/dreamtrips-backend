package com.github.dreamteam.integration;

import com.github.dreamteam.integration.misc.DatabaseCleaner;
import com.github.dreamteam.model.Airport;
import com.github.dreamteam.model.City;
import com.github.dreamteam.model.Country;
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
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CityIntegrationTest extends AbstractTest {

    @Test
    public void get_city_by_id() throws Exception {
        // given
        Country randomCountry = addCountryActionProvider.getObject().execute();

        City randomCity = addCityActionProvider.getObject().setCountry(randomCountry).execute();

        // when
        ResultActions resultActions = mvc.perform(
                get("/cities/" + randomCity.getId())
        );

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(randomCity.getId()), Long.class))
                .andExpect(jsonPath("name", is(randomCity.getName())))
                .andExpect(jsonPath("country.id", is(randomCountry.getId()), Long.class))
                .andExpect(jsonPath("country.name", is(randomCountry.getName())));
    }
}
