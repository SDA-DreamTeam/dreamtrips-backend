package com.github.dreamteam.integration;

import com.github.dreamteam.model.*;
import com.github.dreamteam.pojo.SignInResponse;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookingIntegrationTest extends AbstractTest {

    @Ignore
    @Test
    public void book_trip() throws Exception {
        User admin = addUserActionProvider.getObject().execute();
        SignInResponse adminSession = signInActionProvider.getObject().setUser(admin).execute();

        Country fromCountry = addCountryActionProvider.getObject().setSession(adminSession).setName("Estonia").execute();
        Country toCountry = addCountryActionProvider.getObject().setSession(adminSession).setName("Spain").execute();

        City fromCity = addCityActionProvider.getObject().setSession(adminSession).setCountry(fromCountry).execute();
        City toCity = addCityActionProvider.getObject().setSession(adminSession).setCountry(toCountry).setName("Barcelona").execute();

        Airport fromAirport = addAirportActionProvider.getObject().setSession(adminSession).setCityId(fromCity.getId()).execute();
        Airport toAirport = addAirportActionProvider.getObject().setSession(adminSession).setCityId(toCity.getId()).setName("Barcelona Airport").execute();


        Hotel hotel = addHotelActionProvider.getObject().setSession(adminSession).setCity(toCity).execute();

        Trip trip = addTripActionProvider.getObject().setSession(adminSession)
                .setFromAirport(fromAirport)
                .setToAirport(toAirport)
                .setHotel(hotel)
                .execute();

        SignInResponse userSession = addCustomerActionProvider.getObject().execute();

        Booking booking = addBookingActionProvider.getObject().setSession(userSession)
                .setTripId(trip.getId())
                .setUserId(admin.getId())
                .execute();


        // when
        ResultActions resultActions = listBookingActionProvider.getObject().setSession(userSession).executeApi();

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(booking.getId()), Long.class));
    }

}
