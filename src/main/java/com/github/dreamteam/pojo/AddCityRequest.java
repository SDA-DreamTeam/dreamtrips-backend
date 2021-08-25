package com.github.dreamteam.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddCityRequest {

    @NotNull
    private String name;

    @NotNull
    private long countryId;

    public String getName() {
        return name;
    }

    public AddCityRequest setName(String name) {
        this.name = name;
        return this;
    }

    public AddCityRequest setCountryId(long countryId) {
        this.countryId = countryId;
        return this;
    }

    public long getCountryId() {
        return countryId;
    }
}
