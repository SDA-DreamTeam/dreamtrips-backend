package com.github.dreamteam.integration.action;

import com.github.dreamteam.model.Airport;
import com.github.dreamteam.model.City;
import com.github.dreamteam.model.Country;
import com.github.dreamteam.pojo.AddAirportRequest;
import com.github.dreamteam.pojo.AddCityRequest;
import com.github.dreamteam.pojo.SignInResponse;
import com.github.dreamteam.repository.AirportRepository;
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
public class AddAirportAction {

    @Autowired
    private MockMvc mvc;

    private String name = "Lennart Meri Tallinna lennujaam";
    private long cityId;
    private String token;

    public String getName() {
        return name;
    }

    public AddAirportAction setName(String name) {
        this.name = name;
        return this;
    }

    public long getCityId() {
        return cityId;
    }

    public AddAirportAction setCityId(long cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getToken() {
        return token;
    }

    public AddAirportAction setToken(String token) {
        this.token = token;
        return this;
    }

    public AddAirportAction setSession(SignInResponse session) {
        this.token = session.getToken();
        return this;
    }

    public Airport execute() throws Exception {
        AddAirportRequest request = new AddAirportRequest()
                .setName(name)
                .setCityId(cityId);
        MvcResult addCityMvcResult = mvc.perform(
                post("/airports").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request))
                        .header("Authorization", "Bearer " + token)
        ).andExpect(status().isOk())
                .andReturn();
        return toObject(addCityMvcResult.getResponse().getContentAsString(), Airport.class);
    }
}
