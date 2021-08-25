package com.github.dreamteam.integration;

import com.github.dreamteam.model.*;
import com.github.dreamteam.pojo.SignInResponse;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PurchaseIntegrationTest extends AbstractTest {
    @Test
    public void get_purchase_by_id() throws Exception {

        User admin = addUserActionProvider.getObject().execute();
        SignInResponse session = signInActionProvider.getObject().setUser(admin).execute();

        Country fromCountry = addCountryActionProvider.getObject().setSession(session).setName("Estonia").execute();
        Country toCountry = addCountryActionProvider.getObject().setSession(session).setName("Spain").execute();

        City fromCity = addCityActionProvider.getObject().setSession(session).setCountry(fromCountry).execute();
        City toCity = addCityActionProvider.getObject().setSession(session).setCountry(toCountry).setName("Barcelona").execute();

        Airport fromAirport = addAirportActionProvider.getObject().setSession(session).setCityId(fromCity.getId()).execute();
        Airport toAirport = addAirportActionProvider.getObject().setSession(session).setCityId(toCity.getId()).setName("Barcelona Airport").execute();


        Hotel hotel = addHotelActionProvider.getObject().setSession(session).setCity(toCity).execute();

        Trip trip = addTripActionProvider.getObject().setSession(session)
                .setFromAirport(fromAirport)
                .setToAirport(toAirport)
                .setHotel(hotel)
                .execute();

        BigDecimal amount=new BigDecimal(6000);

        Purchase purchase=addPurchaseActionProvider.getObject().setSession(session)
                .setTripId(trip.getId())
                .execute();


        // when
        ResultActions resultActions = mvc.perform(
                get("/purchases/" + purchase.getId())
        );

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(purchase.getId()), Long.class));


    }

}
