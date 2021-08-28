package com.github.dreamteam.integration.action;

import com.github.dreamteam.model.User;
import com.github.dreamteam.pojo.SignInRequest;
import com.github.dreamteam.pojo.SignInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.github.dreamteam.integration.misc.JsonUtil.asJsonString;
import static com.github.dreamteam.integration.misc.JsonUtil.toObject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class SignInAction {

    @Autowired
    private MockMvc mvc;

    private String username;
    private String password;


    public SignInAction setUser(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        return this;
    }

    public SignInAction setUsername(String username) {
        this.username = username;
        return this;
    }

    public SignInAction setPassword(String password) {
        this.password = password;
        return this;
    }

    public SignInResponse execute() throws Exception {
        SignInRequest request = new SignInRequest()
                .setUsername(username)
                .setPassword(password);
        MvcResult addCountryMvcResult = mvc.perform(
                post("/sign-in").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request))
        ).andExpect(status().isOk())
                .andReturn();
        return toObject(addCountryMvcResult.getResponse().getContentAsString(), SignInResponse.class);
    }


}
