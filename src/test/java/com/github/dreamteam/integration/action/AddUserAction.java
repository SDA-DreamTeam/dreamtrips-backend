package com.github.dreamteam.integration.action;

import com.github.dreamteam.model.User;
import com.github.dreamteam.model.UserRole;
import com.github.dreamteam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class AddUserAction {

    @Autowired
    private UserRepository userRepository;

    private String username;
    private String password = "123";
    private UserRole role = UserRole.CUSTOMER;

    public AddUserAction setUsername(String username) {
        this.username = username;
        return this;
    }

    public AddUserAction setPassword(String password) {
        this.password = password;
        return this;
    }

    public AddUserAction admin() {
        this.role = UserRole.ADMIN;
        return this;
    }

    public User execute() {
        User user = new User();
        user.setUsername(Objects.requireNonNull(username));
        user.setPassword(Objects.requireNonNull(password));
        user.setRole(role);
        return userRepository.save(user);
    }
}
