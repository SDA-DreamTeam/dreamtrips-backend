package com.github.dreamteam.integration.action;

import com.github.dreamteam.model.Booking;
import com.github.dreamteam.pojo.AddBookingRequest;
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
public class AddBookingAction {

    @Autowired
    private MockMvc mvc;

    private String participantsDetails = "details";
    private String status = "new";
    private long tripId;
    private long userId;
    private String token;

    public long getUserId() {
        return userId;
    }


    public AddBookingAction setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public String getToken() {
        return token;
    }

    public AddBookingAction setToken(String token) {
        this.token = token;
        return this;
    }

    public String getParticipantsDetails() {
        return participantsDetails;
    }

    public AddBookingAction setParticipantsDetails(String participantsDetails) {
        this.participantsDetails = participantsDetails;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public long getTripId() {
        return tripId;
    }

    public AddBookingAction setTripId(long tripId) {
        this.tripId = tripId;
        return this;
    }

    public AddBookingAction setStatus(String status) {
        this.status = status;
        return this;
    }

    public AddBookingAction setSession(SignInResponse session) {
        this.token = session.getToken();
        return this;
    }

    public Booking execute() throws Exception {
        AddBookingRequest request = new AddBookingRequest()
                .setTripId(tripId);
        MvcResult addBookingMvcResult = mvc.perform(
                post("/bookings").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request))
                        .header("Authorization", "Bearer " + token)
        ).andExpect(status().isOk())
                .andReturn();
        return toObject(addBookingMvcResult.getResponse().getContentAsString(), Booking.class);
    }
}
