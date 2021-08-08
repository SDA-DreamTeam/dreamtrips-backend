package com.github.dreamteam.model;

import javax.persistence.*;

@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;
    private String name;

    @ManyToOne
    @JoinColumn(name="CITY_ID", nullable=false, updatable=false)
    private City city;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
