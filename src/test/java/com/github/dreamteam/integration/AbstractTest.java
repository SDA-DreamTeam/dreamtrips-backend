package com.github.dreamteam.integration;

import com.github.dreamteam.integration.action.*;
import com.github.dreamteam.integration.misc.DatabaseCleaner;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public abstract class AbstractTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectProvider<AddCountryAction> addCountryActionProvider;

    @Autowired
    protected ObjectProvider<AddCityAction> addCityActionProvider;

    @Autowired
    protected ObjectProvider<AddHotelAction> addHotelActionProvider;

    @Autowired
    protected ObjectProvider<AddAirportAction> addAirportActionProvider;

    @Autowired
    protected  ObjectProvider<AddTripAction> addTripActionProvider;

    @Autowired
    protected  ObjectProvider<AddUserAction> addUserActionProvider;

    @Autowired
    protected  ObjectProvider<SignInAction> signInActionProvider;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Before
    public void setUp() {
        databaseCleaner.clean();
    }

}
