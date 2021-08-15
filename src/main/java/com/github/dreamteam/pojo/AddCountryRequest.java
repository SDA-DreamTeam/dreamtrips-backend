package com.github.dreamteam.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddCountryRequest {

    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public AddCountryRequest setName(String name) {
        this.name = name;
        return this;
    }
}
