package com.github.dreamteam.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddPurchaseRequest {

    private String participantsDetails;
    private BigDecimal amount;
    private String status;

    @NotNull
    private long tripId;

    public String getParticipantsDetails() {
        return participantsDetails;
    }

    public AddPurchaseRequest setParticipantsDetails(String participantsDetails) {
        this.participantsDetails = participantsDetails;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public AddPurchaseRequest setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public AddPurchaseRequest setStatus(String status) {
        this.status = status;
        return this;
    }

    public long getTripId() {
        return tripId;
    }

    public AddPurchaseRequest setTripId(long tripId) {
        this.tripId = tripId;
        return this;
    }
}
