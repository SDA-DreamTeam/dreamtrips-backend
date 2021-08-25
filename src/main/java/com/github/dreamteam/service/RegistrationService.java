package com.github.dreamteam.service;

import com.github.dreamteam.model.User;
import com.github.dreamteam.model.UserRole;
import com.github.dreamteam.pojo.RegistrationRequest;
import com.github.dreamteam.pojo.SignInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class RegistrationService {

    @Autowired
    private AuthService authService;

    public SignInResponse register(RegistrationRequest request, HttpServletRequest httpServletRequest) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setRole(UserRole.CUSTOMER);

        user = authService.signUp(user);
        return authService.signIn(user,httpServletRequest);
    }
}
