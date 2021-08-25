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

    @Test
    public void find_all_by_from_airport() throws Exception {
        // given
        User admin = addUserActionProvider.getObject().execute();
        SignInResponse session = signInActionProvider.getObject().setUser(admin).execute();

        Country estonia = addCountryActionProvider.getObject().setSession(session).setName("Estonia").execute();
        Country spain = addCountryActionProvider.getObject().setSession(session).setName("Spain").execute();

        City tallinn = addCityActionProvider.getObject().setSession(session).setCountry(estonia).execute();
        City barcelona = addCityActionProvider.getObject().setSession(session).setCountry(spain).setName("Barcelona").execute();
        City malaga = addCityActionProvider.getObject().setSession(session).setCountry(spain).setName("Malaga").execute();

        Airport tallinnAirport = addAirportActionProvider.getObject().setSession(session).setCityId(tallinn.getId()).execute();
        Airport barcelonaAirport = addAirportActionProvider.getObject().setSession(session).setCityId(barcelona.getId()).setName("Barcelona Airport").execute();
        Airport malagaAirport = addAirportActionProvider.getObject().setSession(session).setCityId(barcelona.getId()).setName("Malaga Airport").execute();

        Hotel barcelonaHotel = addHotelActionProvider.getObject().setSession(session).setCity(barcelona).execute();
        Hotel malagaHotel = addHotelActionProvider.getObject().setSession(session).setCity(malaga).execute();

        Trip firstTrip = addTripActionProvider.getObject()
                .setSession(session)
                .setFromAirport(tallinnAirport)
                .setToAirport(barcelonaAirport)
                .setHotel(barcelonaHotel)
                .execute();

        Trip secondTrip = addTripActionProvider.getObject()
                .setSession(session)
                .setFromAirport(tallinnAirport)
                .setToAirport(malagaAirport)
                .setHotel(malagaHotel)
                .execute();

        // when
        Page<Trip> page = findTripActionProvider.getObject()
                .setFromAirportId(tallinnAirport.getId())
                .setToAirportId(barcelonaAirport.getId())
                .execute();

        // then
        Assert.assertEquals(1, page.getSize());
    }

}
