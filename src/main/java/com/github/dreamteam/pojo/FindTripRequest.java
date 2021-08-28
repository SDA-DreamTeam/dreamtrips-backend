package com.github.dreamteam.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.dreamteam.model.BoardBasis;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FindTripRequest {
    private Long fromAirportId;
    private Long toAirportId;

    private Long fromCountryId;
    private Long toCountryId;

    private Long hotelId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate departureDate;
    private Integer numberOfDays;
    private BoardBasis type;
    private BigDecimal priceAdult;
    private BigDecimal priceChild;
    private Integer numberOfBedsAdult;
    private Integer numberOfBedsChild;
    private int page = 0;
    private int size = 10;

    public Long getFromAirportId() {
        return fromAirportId;
    }

    public FindTripRequest setFromAirportId(Long fromAirportId) {
        this.fromAirportId = fromAirportId;
        return this;
    }

    public Long getToAirportId() {
        return toAirportId;
    }

    public FindTripRequest setToAirportId(Long toAirportId) {
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

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public FindTripRequest setNumberOfDays(Integer numberOfDays) {
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

    public Integer getNumberOfBedsAdult() {
        return numberOfBedsAdult;
    }

    public FindTripRequest setNumberOfBedsAdult(Integer numberOfBedsAdult) {
        this.numberOfBedsAdult = numberOfBedsAdult;
        return this;
    }

    public Integer getNumberOfBedsChild() {
        return numberOfBedsChild;
    }

    public FindTripRequest setNumberOfBedsChild(Integer numberOfBedsChild) {
        this.numberOfBedsChild = numberOfBedsChild;
        return this;
    }

    public Long getFromCountryId() {
        return fromCountryId;
    }

    public FindTripRequest setFromCountryId(Long fromCountryId) {
        this.fromCountryId = fromCountryId;
        return this;
    }

    public Long getToCountryId() {
        return toCountryId;
    }

    public FindTripRequest setToCountryId(Long toCountryId) {
        this.toCountryId = toCountryId;
        return this;
    }

    public int getPage() {
        return page;
    }

    public FindTripRequest setPage(int page) {
        this.page = page;
        return this;
    }

    public int getSize() {
        return size;
    }

    public FindTripRequest setSize(int size) {
        this.size = size;
        return this;
    }
}
