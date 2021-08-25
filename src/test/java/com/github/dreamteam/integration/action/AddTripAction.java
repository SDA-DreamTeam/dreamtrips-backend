package com.github.dreamteam.integration.action;

import com.github.dreamteam.model.Airport;
import com.github.dreamteam.model.BoardBasis;
import com.github.dreamteam.model.Hotel;
import com.github.dreamteam.model.Trip;
import com.github.dreamteam.pojo.AddHotelRequest;
import com.github.dreamteam.pojo.AddTripRequest;
import com.github.dreamteam.pojo.SignInResponse;
import com.github.dreamteam.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static com.github.dreamteam.integration.misc.JsonUtil.asJsonString;
import static com.github.dreamteam.integration.misc.JsonUtil.toObject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class AddTripAction {
    @Autowired
    private MockMvc mvc;

    private long fromAirportId;
    private long toAirportId;
    private long hotelId;
    private LocalDate departureDate = LocalDate.now();
    private int numberOfDays = 7;
    private BoardBasis type = BoardBasis.BB;
    private BigDecimal priceAdult = new BigDecimal(300);
    private BigDecimal priceChild = new BigDecimal(150);
    private int numberOfBedsAdult = 2;
    private int numberOfBedsChild = 2;
    private String token;

    public long getFromAirportId() {
        return fromAirportId;
    }

    public AddTripAction setFromAirport(Airport fromAirport) {
        this.fromAirportId = fromAirport.getId();
        return this;
    }

    public long getToAirportId() {
        return toAirportId;
    }

    public AddTripAction setToAirport(Airport toAirport) {
        this.toAirportId = toAirport.getId();
        return this;
    }

    public long getHotelId() {
        return hotelId;
    }

    public AddTripAction setHotel(Hotel hotel) {
        this.hotelId = hotel.getId();
        return this;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public AddTripAction setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
        return this;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public AddTripAction setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
        return this;
    }

    public BoardBasis getType() {
        return type;
    }

    public AddTripAction setType(BoardBasis type) {
        this.type = type;
        return this;
    }

    public BigDecimal getPriceAdult() {
        return priceAdult;
    }

    public AddTripAction setPriceAdult(BigDecimal priceAdult) {
        this.priceAdult = priceAdult;
        return this;
    }

    public BigDecimal getPriceChild() {
        return priceChild;
    }

    public AddTripAction setPriceChild(BigDecimal priceChild) {
        this.priceChild = priceChild;
        return this;
    }

    public int getNumberOfBedsAdult() {
        return numberOfBedsAdult;
    }

    public AddTripAction setNumberOfBedsAdult(int numberOfBedsAdult) {
        this.numberOfBedsAdult = numberOfBedsAdult;
        return this;
    }

    public int getNumberOfBedsChild() {
        return numberOfBedsChild;
    }

    public AddTripAction setNumberOfBedsChild(int numberOfBedsChild) {
        this.numberOfBedsChild = numberOfBedsChild;
        return this;
    }

    public String getToken() {
        return token;
    }

    public AddTripAction setToken(String token) {
        this.token = token;
        return this;
    }

    public AddTripAction setSession(SignInResponse session) {
        this.token = session.getToken();
        return this;
    }

    public Trip execute() throws Exception {
        AddTripRequest request = new AddTripRequest()
                .setFromAirport(fromAirportId)
                .setToAirport(toAirportId)
                .setHotelId(hotelId);
        MvcResult addTripMvcResult = mvc.perform(
                post("/trips").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request))
                        .header("Authorization", "Bearer " + token)
        ).andExpect(status().isOk())
                .andReturn();
        return toObject(addTripMvcResult.getResponse().getContentAsString(), Trip.class);
    }

}
