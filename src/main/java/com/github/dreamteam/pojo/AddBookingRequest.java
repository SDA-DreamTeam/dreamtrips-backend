package com.github.dreamteam.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddBookingRequest {

    @NotNull
    private long tripId;

    private String participantsDetails;

    public String getParticipantsDetails() {
        return participantsDetails;
    }

    public AddBookingRequest setParticipantsDetails(String participantsDetails) {
        this.participantsDetails = participantsDetails;
        return this;
    }

    public long getTripId() {
        return tripId;
    }

    public AddBookingRequest setTripId(long tripId) {
        this.tripId = tripId;
        return this;
    }
}
