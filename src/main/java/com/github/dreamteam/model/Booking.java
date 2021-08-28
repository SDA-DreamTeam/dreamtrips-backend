package com.github.dreamteam.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false, updatable = false)
    private User user;

    //private LocalDateTime created;

    private String participantsDetails;

    @OneToOne
    @JoinColumn(name = "TRIP_ID", nullable = false, updatable = false)
    private Trip trip;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Booking setUser(User user) {
        this.user = user;
        return this;
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


    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    // public LocalDateTime getCreated() {
    //    return created;
    //}

    //public Booking setCreated(LocalDateTime created) {
    //    this.created = created;
    //    return this;
    //}
}
