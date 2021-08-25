package com.github.dreamteam.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.dreamteam.model.BoardBasis;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FindTripRequest {
    @NotNull
    private long fromAirportId;

    @NotNull
    private long toAirportId;

    private Long hotelId;
    private LocalDate departureDate;
    private int numberOfDays;
    private BoardBasis type;
    private BigDecimal priceAdult;
    private BigDecimal priceChild;
    private int numberOfBedsAdult;
    private int numberOfBedsChild;

    public long getFromAirportId() {
        return fromAirportId;
    }

    public FindTripRequest setFromAirportId(long fromAirportId) {
        this.fromAirportId = fromAirportId;
        return this;
    }

    public long getToAirportId() {
        return toAirportId;
    }

    public FindTripRequest setToAirportId(long toAirportId) {
        this.toAirportId = toAirportId;
        return this;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public FindTripRequest setHotelId(Long hotelId) {
        this.hotelId = hotelId;
        return this;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public FindTripRequest setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
        return this;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public FindTripRequest setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
        return this;
    }

    public BoardBasis getType() {
        return type;
    }

    public FindTripRequest setType(BoardBasis type) {
        this.type = type;
        return this;
    }

    public BigDecimal getPriceAdult() {
        return priceAdult;
    }

    public FindTripRequest setPriceAdult(BigDecimal priceAdult) {
        this.priceAdult = priceAdult;
        return this;
    }

    public BigDecimal getPriceChild() {
        return priceChild;
    }

    public FindTripRequest setPriceChild(BigDecimal priceChild) {
        this.priceChild = priceChild;
        return this;
    }

    public int getNumberOfBedsAdult() {
        return numberOfBedsAdult;
    }

    public FindTripRequest setNumberOfBedsAdult(int numberOfBedsAdult) {
        this.numberOfBedsAdult = numberOfBedsAdult;
        return this;
    }

    public int getNumberOfBedsChild() {
        return numberOfBedsChild;
    }

    public FindTripRequest setNumberOfBedsChild(int numberOfBedsChild) {
        this.numberOfBedsChild = numberOfBedsChild;
        return this;
    }
}
