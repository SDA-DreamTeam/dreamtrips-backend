package com.github.dreamteam.integration.action;

import com.github.dreamteam.model.Purchase;
import com.github.dreamteam.model.Trip;
import com.github.dreamteam.pojo.AddPurchaseRequest;
import com.github.dreamteam.pojo.AddTripRequest;
import com.github.dreamteam.pojo.SignInResponse;
import com.github.dreamteam.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static com.github.dreamteam.integration.misc.JsonUtil.asJsonString;
import static com.github.dreamteam.integration.misc.JsonUtil.toObject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class AddPurchaseAction {

    @Autowired
    private MockMvc mvc;

    private String participantsDetails="details";
    private BigDecimal amount= new BigDecimal(4500);
    private String status="active";
    private long tripId;
    private String token;

    public String getToken() {
        return token;
    }

    public AddPurchaseAction setToken(String token) {
        this.token = token;
        return this;
    }

    public String getParticipantsDetails() {
        return participantsDetails;
    }

    public AddPurchaseAction setParticipantsDetails(String participantsDetails) {
        this.participantsDetails = participantsDetails;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public AddPurchaseAction setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public long getTripId() {
        return tripId;
    }

    public AddPurchaseAction setTripId(long tripId) {
        this.tripId = tripId;
        return this;
    }

    public AddPurchaseAction setStatus(String status) {
        this.status = status;
        return this;
    }
    public AddPurchaseAction setSession(SignInResponse session) {
        this.token = session.getToken();
        return this;
    }

    public Purchase execute() throws Exception {
        AddPurchaseRequest request = new AddPurchaseRequest()
                .setTripId(tripId)
                .setAmount(amount)
                .setStatus(status);
        MvcResult addPurchaseMvcResult = mvc.perform(
                post("/purchases").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request))
                        .header("Authorization", "Bearer " + token)
        ).andExpect(status().isOk())
                .andReturn();
        return toObject(addPurchaseMvcResult.getResponse().getContentAsString(), Purchase.class);
    }
}
