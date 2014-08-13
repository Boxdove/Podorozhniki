package com.epam.podorozhniki.us.US_04_2_1;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;
import com.epam.podorozhniki.ui.RegistrationPage;
import org.jbehave.core.annotations.*;

import java.sql.SQLException;

/**
 * Created by Viktoriia_Ishchuk on 8/1/2014.
 */
public class usRunner0421 {

    private MainPageBeforeLogin mainPageBeforeLogin;
    private MainPageAfterLogin mainPageAfterLogin;
    private RegistrationPage registrationPage;

    @BeforeScenario
    public void setUp(){
        Driver.init();
        Driver.getInstance().manage().window().maximize();
    }

    @AfterScenario
    public void tearDown(){
        Driver.tearDown();
    }

    @Given("main podorozhniki page")
    public void givenMainPage() {
        Driver.getInstance().get("http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main");
        mainPageBeforeLogin = new MainPageBeforeLogin();
    }

    @Given("registration form")
    public void givenRegistrationPage() {
        registrationPage = mainPageBeforeLogin.regLink();
    }

    @When("the user has entered registration information")
    public void whenEnteredInformation() {
        registrationPage.typeLogin();
        registrationPage.typePassword();
        registrationPage.confirmPassword();
        registrationPage.typephone();
        registrationPage.typeEmail();
    }

    @When("rules are accepted")
    public void rulesAccepted() {
        registrationPage.agreeChekButton();
    }

    @When("confirm button are pressed")
    public void confirmPressed() {
        mainPageAfterLogin = registrationPage.typeSubmitButton();
    }

    @Then("the user should see error message under field login")
    public void seeMessage() throws SQLException {registrationPage.verifyError(System.getProperty("US0421.errormessage")); }

    @Then("the user should see logout button")
    public void seeLogout() { mainPageAfterLogin.verifyLogoutButton(); }

    @Then("the user name should be displayed above the photo field")
    public void nameDisplayed() {
        mainPageAfterLogin.verifyNameDisplayed(System.getProperty("US0421.login"));
    }

}
