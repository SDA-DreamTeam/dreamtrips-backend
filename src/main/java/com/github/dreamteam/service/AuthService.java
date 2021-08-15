package com.github.dreamteam.service;

import com.github.dreamteam.exception.NotFoundException;
import com.github.dreamteam.model.User;
import com.github.dreamteam.pojo.SignInRequest;
import com.github.dreamteam.pojo.SignInResponse;
import com.github.dreamteam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public SignInResponse signIn(SignInRequest request, HttpServletRequest httpServletRequest) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found"));
        String userPassword = user.getPassword();
        String requestPassword = request.getPassword();
        if (!MessageDigest.isEqual(userPassword.getBytes(StandardCharsets.UTF_8), requestPassword.getBytes(StandardCharsets.UTF_8))) {
            throw new NotFoundException("User not found");
        }
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, user.getUsername());
        return new SignInResponse().setToken(session.getId());
    }
}
