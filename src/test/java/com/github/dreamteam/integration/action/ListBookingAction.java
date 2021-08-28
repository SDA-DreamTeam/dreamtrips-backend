package com.github.dreamteam.integration.action;

import com.github.dreamteam.model.Booking;
import com.github.dreamteam.pojo.SignInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static com.github.dreamteam.integration.misc.JsonUtil.toPage;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class ListBookingAction {

    @Autowired
    private MockMvc mvc;

    private String token;

    public ListBookingAction setSession(SignInResponse session) {
        this.token = session.getToken();
        return this;
    }


    public Page<Booking> execute() throws Exception {
        MvcResult addTripMvcResult = executeApi()
                .andExpect(status().isOk())
                .andReturn();
        return toPage(addTripMvcResult.getResponse().getContentAsString(), Booking.class);
    }

    public ResultActions executeApi() throws Exception {
        return mvc.perform(
                get("/bookings")
                        .header("Authorization", "Bearer " + token)
        );
    }
}
