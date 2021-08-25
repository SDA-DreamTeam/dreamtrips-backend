package com.github.dreamteam.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddHotelRequest {
    @NotNull
    private String name;

    @NotNull
    private long cityId;

    public String getName() {
        return name;
    }

    public AddHotelRequest setName(String name) {
        this.name = name;
        return this;
    }

    public long getCityId() {
        return cityId;
    }

    public AddHotelRequest setCityId(long cityId) {
        this.cityId = cityId;
        return this;
    }
}
