package com.github.dreamteam.integration.action;

import com.github.dreamteam.model.City;
import com.github.dreamteam.model.Hotel;
import com.github.dreamteam.pojo.AddHotelRequest;
import com.github.dreamteam.pojo.SignInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static com.github.dreamteam.integration.misc.JsonUtil.asJsonString;
import static com.github.dreamteam.integration.misc.JsonUtil.toObject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class AddHotelAction {

    @Autowired
    private MockMvc mvc;

    private String name = "Hilton";
    private long cityId;
    private String token;

    public String getName() {
        return name;
    }

    public AddHotelAction setName(String name) {
        this.name = name;
        return this;
    }

    public long getCityId() {
        return cityId;
    }

    public AddHotelAction setCity(City city) {
        this.cityId = city.getId();
        return this;
    }

    public String getToken() {
        return token;
    }

    public AddHotelAction setToken(String token) {
        this.token = token;
        return this;
    }
    public AddHotelAction setSession(SignInResponse session) {
        this.token = session.getToken();
        return this;
    }

    public Hotel execute() throws Exception {
        AddHotelRequest request = new AddHotelRequest()
                .setName(name)
                .setCityId(cityId);
        MvcResult addHotelMvcResult = mvc.perform(
                post("/hotels").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request))
                        .header("Authorization", "Bearer " + token)
        ).andExpect(status().isOk())
                .andReturn();
        return toObject(addHotelMvcResult.getResponse().getContentAsString(), Hotel.class);
    }

}
