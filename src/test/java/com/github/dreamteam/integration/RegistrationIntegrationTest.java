package com.github.dreamteam.integration;

import com.github.dreamteam.model.User;
import com.github.dreamteam.pojo.SignInResponse;
import org.junit.Test;

public class RegistrationIntegrationTest extends AbstractTest {

    @Test
    public void add_customer_test() throws Exception {
        SignInResponse session= addCustomerActionProvider.getObject().execute();
    }
}
