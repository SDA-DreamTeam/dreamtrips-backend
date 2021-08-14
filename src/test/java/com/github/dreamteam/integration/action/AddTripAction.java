package com.github.dreamteam.integration.action;

import com.github.dreamteam.model.Airport;
import com.github.dreamteam.model.BoardBasis;
import com.github.dreamteam.model.Hotel;
import com.github.dreamteam.model.Trip;
import com.github.dreamteam.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class AddTripAction {
    @Autowired
    private TripRepository tripRepository;

    private Airport fromAirport;
    private Airport toAirport;
    private Hotel hotel;
    private LocalDate departureDate = LocalDate.now();
    private int numberOfDays = 7;
    private BoardBasis type = BoardBasis.BB;
    private BigDecimal priceAdult = new BigDecimal(300);
    private BigDecimal priceChild = new BigDecimal(150);
    private int numberOfBedsAdult = 2;
    private int numberOfBedsChild = 2;

    public AddTripAction setFromAirport(Airport fromAirport) {
        this.fromAirport = fromAirport;
        return this;
    }

    public AddTripAction setToAirport(Airport toAirport) {
        this.toAirport = toAirport;
        return this;
    }

    public AddTripAction setHotel(Hotel hotel) {
        this.hotel = hotel;
        return this;
    }

    public AddTripAction setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
        return this;
    }

    public AddTripAction setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
        return this;
    }

    public AddTripAction setType(BoardBasis type) {
        this.type = type;
        return this;
    }

    public AddTripAction setPrice(BigDecimal priceAdult, BigDecimal priceChild) {
        this.priceAdult = priceAdult;
        this.priceChild = priceChild;
        return this;
    }

    public AddTripAction setNumberOfBeds(int numberOfBedsAdult, int numberOfBedsChild) {
        this.numberOfBedsAdult = numberOfBedsAdult;
        this.numberOfBedsChild = numberOfBedsChild;
        return this;
    }

    public Trip execute() {
        Trip trip = new Trip();
        trip.setFromAirport(Objects.requireNonNull(fromAirport));
        trip.setToAirport(Objects.requireNonNull(toAirport));
        trip.setHotel(hotel);
        trip.setDepartureDate(departureDate);
        trip.setNumberOfDays(numberOfDays);
        trip.setType(type);
        trip.setPriceAdult(priceAdult);
        trip.setPriceChild(priceChild);
        trip.setNumberOfBedsAdult(numberOfBedsAdult);
        trip.setNumberOfBedsChild(numberOfBedsChild);

        return tripRepository.save(trip);

    }

}
