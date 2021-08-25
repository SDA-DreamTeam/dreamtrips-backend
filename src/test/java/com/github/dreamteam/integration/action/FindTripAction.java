package com.github.dreamteam.integration.action;

import com.github.dreamteam.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static com.github.dreamteam.integration.misc.JsonUtil.toPage;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class FindTripAction {

    @Autowired
    private MockMvc mvc;

    private long fromAirportId;

    private long toAirportId;

    private Long hotelId;

    public FindTripAction setFromAirportId(long fromAirportId) {
        this.fromAirportId = fromAirportId;
        return this;
    }

    public FindTripAction setToAirportId(long toAirportId) {
        this.toAirportId = toAirportId;
        return this;
    }

    public FindTripAction setHotelId(Long hotelId) {
        this.hotelId = hotelId;
        return this;
    }

    public Page<Trip> execute() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.put("fromAirportId", List.of(String.valueOf(fromAirportId)));
        queryParams.put("toAirportId", List.of(String.valueOf(toAirportId)));
        if (hotelId != null) {
            queryParams.put("hotelId", List.of(String.valueOf(hotelId)));
        }

        MvcResult addTripMvcResult = mvc.perform(
                get("/trips")
                        .queryParams(queryParams)
        ).andExpect(status().isOk())
                .andReturn();
        return toPage(addTripMvcResult.getResponse().getContentAsString(), Trip.class);
    }
}
