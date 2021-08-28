package com.github.dreamteam.integration;

import com.github.dreamteam.model.User;
import com.github.dreamteam.pojo.SignInResponse;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProfileTest extends AbstractTest {
    @Test
    public void get_profile() throws Exception {
        SignInResponse session = addCustomerActionProvider.getObject().setUsername("blabla").execute();

        ResultActions resultActions = mvc.perform(
                get("/profile/")
                        .header("Authorization", "Bearer " + session.getToken())
        );

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("username", is("blabla")))
                .andExpect(jsonPath("role", is("CUSTOMER")));



    }
}

