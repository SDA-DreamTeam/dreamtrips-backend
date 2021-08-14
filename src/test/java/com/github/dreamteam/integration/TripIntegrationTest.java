package com.github.dreamteam.integration;

import com.github.dreamteam.model.*;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TripIntegrationTest extends AbstractTest {

    @Test
    public void get_trip_by_id() throws Exception {
        // given
        Country fromCountry = addCountryActionProvider.getObject().execute();
        Country toCountry = addCountryActionProvider.getObject().setName("Spain").execute();

        City fromCity = addCityActionProvider.getObject().setCountry(fromCountry).execute();
        City toCity = addCityActionProvider.getObject().setCountry(toCountry).setName("Barcelona").execute();

        Airport fromAirport = addAirportActionProvider.getObject().setCity(fromCity).execute();
        Airport toAirport = addAirportActionProvider.getObject().setCity(toCity).setName("Barcelona Airport").execute();

        Hotel hotel = addHotelActionProvider.getObject().setCity(toCity).execute();

        Trip trip = addTripActionProvider.getObject()
                .setFromAirport(fromAirport)
                .setToAirport(toAirport)
                .setHotel(hotel)
                .execute();

        // when
        ResultActions resultActions = mvc.perform(
                get("/trips/" + trip.getId())
        );

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(trip.getId()), Long.class))
                .andExpect(jsonPath("fromAirport.id", is(fromAirport.getId()), Long.class))
                .andExpect(jsonPath("toAirport.id", is(toAirport.getId()), Long.class))
                .andExpect(jsonPath("hotel.id", is(hotel.getId()), Long.class));
    }

}
