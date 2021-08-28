package com.github.dreamteam.controller;

import com.github.dreamteam.model.User;
import com.github.dreamteam.pojo.ProfileResponse;
import com.github.dreamteam.pojo.SignInRequest;
import com.github.dreamteam.pojo.SignInResponse;
import com.github.dreamteam.service.AuthService;
import com.github.dreamteam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/sign-in", consumes = "application/json")
    public SignInResponse signIn(@RequestBody @Valid SignInRequest request, HttpServletRequest servletRequest) {
        return authService.signIn(request, servletRequest);
    }

    @GetMapping(value= "/profile")
    @ResponseBody
    public ProfileResponse profile(Authentication authentication){
        User user = userService.getUser(authentication.getName());
        return new ProfileResponse().setUsername(user.getUsername()).setRole(user.getRole());
    }
}
