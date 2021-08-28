package com.github.dreamteam.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "FROM_AIRPORT_ID", nullable = false, updatable = false)
    private Airport fromAirport;

    @ManyToOne
    @JoinColumn(name = "TO_AIRPORT_ID", nullable = false, updatable = false)
    private Airport toAirport;

    @ManyToOne
    @JoinColumn(name = "HOTEL_ID", nullable = false, updatable = false)
    private Hotel hotel;

    private LocalDate departureDate;
    private int numberOfDays;

    @Enumerated(EnumType.STRING)
    private BoardBasis type;

    private BigDecimal priceAdult;
    private BigDecimal priceChild;
    private int numberOfBedsAdult;
    private int numberOfBedsChild;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Airport getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(Airport fromAirport) {
        this.fromAirport = fromAirport;
    }

    public Airport getToAirport() {
        return toAirport;
    }

    public void setToAirport(Airport toAirport) {
        this.toAirport = toAirport;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public BoardBasis getType() {
        return type;
    }

    public void setType(BoardBasis type) {
        this.type = type;
    }

    public BigDecimal getPriceAdult() {
        return priceAdult;
    }

    public void setPriceAdult(BigDecimal priceAdult) {
        this.priceAdult = priceAdult;
    }

    public BigDecimal getPriceChild() {
        return priceChild;
    }

    public void setPriceChild(BigDecimal priceChild) {
        this.priceChild = priceChild;
    }

    public int getNumberOfBedsAdult() {
        return numberOfBedsAdult;
    }

    public void setNumberOfBedsAdult(int numberOfBedsAdult) {
        this.numberOfBedsAdult = numberOfBedsAdult;
    }

    public int getNumberOfBedsChild() {
        return numberOfBedsChild;
    }

    public void setNumberOfBedsChild(int numberOfBedsChild) {
        this.numberOfBedsChild = numberOfBedsChild;
    }
}
