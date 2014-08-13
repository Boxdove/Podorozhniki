package com.epam.podorozhniki.core;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void init() {
        Driver.init();
    }

    @AfterMethod
    public void cleanup() {
        Driver.tearDown();
    }
}
