package com.github.dreamteam.integration.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dreamteam.model.Country;
import com.github.dreamteam.pojo.AddCountryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class AddCountryAction {

    @Autowired
    private MockMvc mvc;

    private String name = "Estonia";
    private String token;

    public AddCountryAction setName(String name) {
        this.name = name;
        return this;
    }

    public AddCountryAction setToken(String token) {
        this.token = token;
        return this;
    }

    public Country execute() throws Exception {
        AddCountryRequest request = new AddCountryRequest()
                .setName(name);
        MvcResult addCountryMvcResult = mvc.perform(
                post("/countries").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request))
                        .header("Authorization", "Bearer " + token)
        ).andExpect(status().isOk())
                .andReturn();
        return toObject(addCountryMvcResult.getResponse().getContentAsString(), Country.class);
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T toObject(final String json, Class<T> result) {
        try {
            return new ObjectMapper().readValue(json, result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
