package com.github.dreamteam.integration;

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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AirportIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Before
    public void setUp() {
        airportRepository.deleteAll();
        cityRepository.deleteAll();
        countryRepository.deleteAll();
    }

    @Test
    public void get_airport_by_id() throws Exception {
        // given
        Country country = new Country();
        country.setName("Estonia");
        country = countryRepository.save(country);

        City city = new City();
        city.setCountry(country);
        city.setName("Tallinn");
        city = cityRepository.save(city);

        Airport airport = new Airport();
        airport.setCity(city);
        airport.setName("Tallinn lennujaam");
        airport = airportRepository.save(airport);

        // when
        ResultActions resultActions = mvc.perform(
                get("/airports/" + airport.getId())
        );

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(airport.getId()), Long.class))
                .andExpect(jsonPath("name", is(airport.getName())))
                .andExpect(jsonPath("city.id", is(city.getId()), Long.class))
                .andExpect(jsonPath("city.name", is(city.getName())))
                .andExpect(jsonPath("city.country.id", is(country.getId()), Long.class))
                .andExpect(jsonPath("city.country.name", is(country.getName())));
    }
}
