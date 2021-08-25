package com.github.dreamteam.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String participantsDetails;
    private BigDecimal amount;

    @OneToOne
    @JoinColumn(name="TRIP_ID", nullable=false, updatable=false)
    private Trip trip;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getParticipantsDetails() {
        return participantsDetails;
    }

    public void setParticipantsDetails(String participantsDetails) {
        this.participantsDetails = participantsDetails;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
