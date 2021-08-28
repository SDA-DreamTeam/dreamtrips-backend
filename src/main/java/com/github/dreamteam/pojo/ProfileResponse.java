package com.github.dreamteam.pojo;

import com.github.dreamteam.model.UserRole;

public class ProfileResponse {
    private String username;
    private UserRole role;

    public String getUsername() {
        return username;
    }

    public ProfileResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserRole getRole() {
        return role;
    }

    public ProfileResponse setRole(UserRole role) {
        this.role = role;
        return this;
    }
}
