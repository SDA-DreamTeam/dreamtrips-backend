package com.github.dreamteam.service;

import com.github.dreamteam.exception.NotFoundException;
import com.github.dreamteam.model.User;
import com.github.dreamteam.pojo.SignInRequest;
import com.github.dreamteam.pojo.SignInResponse;
import com.github.dreamteam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public SignInResponse signIn(SignInRequest request, HttpServletRequest httpServletRequest) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found"));
        String userPassword = user.getPassword();
        String requestPassword = request.getPassword();
        if (!MessageDigest.isEqual(userPassword.getBytes(StandardCharsets.UTF_8), requestPassword.getBytes(StandardCharsets.UTF_8))) {
            throw new NotFoundException("User not found");
        }
        return signIn(user, httpServletRequest);
    }

    public SignInResponse signIn(User user, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, user.getUsername());
        return new SignInResponse().setToken(session.getId());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        final String role = "ROLE_" + user.getRole().name();
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(role))
        );
    }

    public User signUp(User user) {

        boolean userExists = userRepository
                .findByUsername(user.getUsername())
                .isPresent();
        if (userExists) {
            throw new IllegalStateException("username already taken");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }
}
