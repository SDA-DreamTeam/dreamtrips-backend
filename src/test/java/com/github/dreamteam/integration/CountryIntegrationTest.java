package com.github.dreamteam.integration;

import com.github.dreamteam.model.Country;
import com.github.dreamteam.model.User;
import com.github.dreamteam.pojo.SignInResponse;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CountryIntegrationTest extends AbstractTest {


    @Test
    public void get_country_by_id() throws Exception {
        // given
        User admin = addUserActionProvider.getObject().execute();
        SignInResponse session = signInActionProvider.getObject().setUser(admin).execute();

        Country randomCountry = addCountryActionProvider.getObject().setSession(session).execute();

        // when
        ResultActions resultActions = mvc.perform(
                get("/countries/" + randomCountry.getId())
        );

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(randomCountry.getId()), Long.class))
                .andExpect(jsonPath("name", is(randomCountry.getName())));
    }
}
