package com.github.dreamteam.controller;

import com.github.dreamteam.pojo.SignInRequest;
import com.github.dreamteam.pojo.SignInResponse;
import com.github.dreamteam.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/sign-in", consumes = "application/json")
    public SignInResponse signIn(@RequestBody @Valid SignInRequest request, HttpServletRequest servletRequest) {
        return authService.signIn(request, servletRequest);
    }
}
