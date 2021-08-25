package com.github.dreamteam.integration.action;

import com.github.dreamteam.model.Purchase;
import com.github.dreamteam.model.User;
import com.github.dreamteam.model.UserRole;
import com.github.dreamteam.pojo.RegistrationRequest;
import com.github.dreamteam.pojo.SignInResponse;
import com.github.dreamteam.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Objects;

import static com.github.dreamteam.integration.misc.JsonUtil.asJsonString;
import static com.github.dreamteam.integration.misc.JsonUtil.toObject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class AddCustomerAction {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mvc;

    private String username = RandomStringUtils.randomAscii(10);
    private String password = "123";
    private String email = "123@gmail.com";


    public AddCustomerAction setUsername(String username) {
        this.username = username;
        return this;
    }

    public AddCustomerAction setPassword(String password) {
        this.password = password;
        return this;
    }

    public SignInResponse execute() throws Exception {
        RegistrationRequest registrationRequest = new RegistrationRequest()
                .setUsername(username)
                .setPassword(password)
                .setEmail(email);
        MvcResult addCustomerMvcResult = mvc.perform(
                post("/registration").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(registrationRequest))
        ).andExpect(status().isOk())
                .andReturn();
        return toObject(addCustomerMvcResult.getResponse().getContentAsString(), SignInResponse.class);
    }
}
