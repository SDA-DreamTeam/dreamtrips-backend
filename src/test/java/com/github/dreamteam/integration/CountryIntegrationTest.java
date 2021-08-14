package com.github.dreamteam.integration;

import com.github.dreamteam.integration.misc.DatabaseCleaner;
import com.github.dreamteam.model.Country;
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


public class CountryIntegrationTest extends AbstractTest{



    @Test
    public void get_country_by_id() throws Exception {
        // given
        Country randomCountry = addCountryActionProvider.getObject().execute();

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
