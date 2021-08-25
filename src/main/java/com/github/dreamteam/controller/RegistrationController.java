package com.github.dreamteam.controller;

import com.github.dreamteam.pojo.RegistrationRequest;
import com.github.dreamteam.pojo.SignInResponse;
import com.github.dreamteam.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public SignInResponse register(@RequestBody RegistrationRequest request, HttpServletRequest httpServletRequest) {
        return registrationService.register(request, httpServletRequest);
    }
}
