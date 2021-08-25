package com.github.dreamteam.integration.action;

import com.github.dreamteam.model.City;
import com.github.dreamteam.model.Country;
import com.github.dreamteam.pojo.AddCityRequest;
import com.github.dreamteam.pojo.AddCountryRequest;
import com.github.dreamteam.pojo.SignInResponse;
import com.github.dreamteam.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Objects;

import static com.github.dreamteam.integration.misc.JsonUtil.asJsonString;
import static com.github.dreamteam.integration.misc.JsonUtil.toObject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class AddCityAction {

    @Autowired
    private MockMvc mvc;

    private String name = "Tallinn";
    private long countryId;
    private String token;

    public AddCityAction setName(String name) {
        this.name = name;
        return this;
    }

    public AddCityAction setCountry(Country country) {
        this.countryId = country.getId();
        return this;
    }

    public AddCityAction setToken(String token) {
        this.token = token;
        return this;
    }

    public AddCityAction setSession(SignInResponse session) {
        this.token = session.getToken();
        return this;
    }

    public City execute() throws Exception {
        AddCityRequest request = new AddCityRequest()
                .setName(name)
                .setCountryId(countryId);
        MvcResult addCityMvcResult = mvc.perform(
                post("/cities").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request))
                        .header("Authorization", "Bearer " + token)
        ).andExpect(status().isOk())
                .andReturn();
        return toObject(addCityMvcResult.getResponse().getContentAsString(), City.class);
    }
}
