package com.github.dreamteam.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.dreamteam.model.Airport;
import com.github.dreamteam.model.BoardBasis;
import com.github.dreamteam.model.Hotel;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddTripRequest {
    @NotNull
    private long tripId;

    @NotNull
    private long fromAirportId;

    @NotNull
    private long toAirportId;

    @NotNull
    private long hotelId;

    private LocalDate departureDate;
    private int numberOfDays;
    private BoardBasis type;
    private BigDecimal priceAdult;
    private BigDecimal priceChild;
    private int numberOfBedsAdult;
    private int numberOfBedsChild;

    public long getTripId() {
        return tripId;
    }

    public AddTripRequest setTripId(long tripId) {
        this.tripId = tripId;
        return this;
    }

    public long getFromAirportId() {
        return fromAirportId;
    }

    public AddTripRequest setFromAirport(long fromAirportId) {
        this.fromAirportId = fromAirportId;
        return this;
    }

    public long getToAirportId() {
        return toAirportId;
    }

    public AddTripRequest setToAirport(long toAirportId) {
        this.toAirportId = toAirportId;
        return this;
    }

    public long getHotelId() {
        return hotelId;
    }

    public AddTripRequest setHotelId(long hotelId) {
        this.hotelId = hotelId;
        return this;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public AddTripRequest setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
        return this;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public AddTripRequest setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
        return this;
    }

    public BoardBasis getType() {
        return type;
    }

    public AddTripRequest setType(BoardBasis type) {
        this.type = type;
        return this;
    }

    public BigDecimal getPriceAdult() {
        return priceAdult;
    }

    public AddTripRequest setPriceAdult(BigDecimal priceAdult) {
        this.priceAdult = priceAdult;
        return this;
    }

    public BigDecimal getPriceChild() {
        return priceChild;
    }

    public AddTripRequest setPriceChild(BigDecimal priceChild) {
        this.priceChild = priceChild;
        return this;
    }

    public int getNumberOfBedsAdult() {
        return numberOfBedsAdult;
    }

    public AddTripRequest setNumberOfBedsAdult(int numberOfBedsAdult) {
        this.numberOfBedsAdult = numberOfBedsAdult;
        return this;
    }

    public int getNumberOfBedsChild() {
        return numberOfBedsChild;
    }

    public AddTripRequest setNumberOfBedsChild(int numberOfBedsChild) {
        this.numberOfBedsChild = numberOfBedsChild;
        return this;
    }
}
