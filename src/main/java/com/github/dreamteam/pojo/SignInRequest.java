package com.github.dreamteam.pojo;

import javax.validation.constraints.NotNull;

public class SignInRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public SignInRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SignInRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
