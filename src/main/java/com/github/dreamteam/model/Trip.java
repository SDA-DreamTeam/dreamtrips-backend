package com.github.dreamteam.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;

    @ManyToOne
    @JoinColumn(name="FROM_AIRPORT_ID", nullable=false, updatable=false, insertable = false)
    private Airport fromAirport;

    @ManyToOne
    @JoinColumn(name="TO_AIRPORT_ID", nullable=false, updatable=false, insertable = false)
    private Airport toAirport;

    @ManyToOne
    @JoinColumn(name="HOTEL_ID", nullable=false, updatable=false)
    private Hotel hotel;

    private Date departureDate;
    private int numberOfDays;
    private String type;
    private double priceAdult;
    private double priceChild;
    private int numberOfBedsAdult;
    private int numberOfBedsChild;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPriceAdult() {
        return priceAdult;
    }

    public void setPriceAdult(double priceAdult) {
        this.priceAdult = priceAdult;
    }

    public double getPriceChild() {
        return priceChild;
    }

    public void setPriceChild(double priceChild) {
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
