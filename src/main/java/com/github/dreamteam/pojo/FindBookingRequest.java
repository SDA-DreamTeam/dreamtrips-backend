package com.github.dreamteam.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.dreamteam.model.BookingStatus;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FindBookingRequest {
    @NotNull
    private long tripId;

    private String participantsDetails;
    private BookingStatus status;

    public long getTripId() {
        return tripId;
    }

    public String getParticipantsDetails() {
        return participantsDetails;
    }

    public BookingStatus getStatus() {
        return status;
    }
}
