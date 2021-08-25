package com.github.dreamteam.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddAirportRequest {
    @NotNull
    private String name;

    @NotNull
    private long cityId;

    public String getName() {
        return name;
    }

    public AddAirportRequest setName(String name) {
        this.name = name;
        return this;
    }

    public long getCityId() {
        return cityId;
    }

    public AddAirportRequest setCityId(long cityId) {
        this.cityId = cityId;
        return this;
    }
}
