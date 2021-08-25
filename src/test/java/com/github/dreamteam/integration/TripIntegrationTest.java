package com.github.dreamteam.integration;

import com.github.dreamteam.model.*;
import com.github.dreamteam.pojo.SignInResponse;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TripIntegrationTest extends AbstractTest {

    @Test
    public void get_trip_by_id() throws Exception {
        // given
        User admin = addUserActionProvider.getObject().execute();
        SignInResponse session = signInActionProvider.getObject().setUser(admin).execute();

        Country fromCountry = addCountryActionProvider.getObject().setSession(session).setName("Estonia").execute();
        Country toCountry = addCountryActionProvider.getObject().setSession(session).setName("Spain").execute();

        City fromCity = addCityActionProvider.getObject().setSession(session).setCountry(fromCountry).execute();
        City toCity = addCityActionProvider.getObject().setSession(session).setCountry(toCountry).setName("Barcelona").execute();

        Airport fromAirport = addAirportActionProvider.getObject().setSession(session).setCityId(fromCity.getId()).execute();
        Airport toAirport = addAirportActionProvider.getObject().setSession(session).setCityId(toCity.getId()).setName("Barcelona Airport").execute();

        Hotel hotel = addHotelActionProvider.getObject().setSession(session).setCity(toCity).execute();

        Trip trip = addTripActionProvider.getObject()
                .setSession(session)
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
