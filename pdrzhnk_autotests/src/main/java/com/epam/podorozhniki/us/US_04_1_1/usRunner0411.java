package com.epam.podorozhniki.us.US_04_1_1;

import com.epam.podorozhniki.core.Driver;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Viktoriia_Ishchuk on 8/1/2014.
 */
public class usRunner0411 {

    private MainPageBeforeLogin mainPageBeforeLogin;
    private MainPageAfterLogin mainPageAfterLogin;

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

    @When("the user has entered login information into login form")
    public void enterInfo(){
        mainPageBeforeLogin.enterLoginAndPass(System.getProperty("US0411.login"), System.getProperty("US0411.correctpassword"));
    }

    @When("the user has entered incorrect login information into login form")
    public void enterIncorrectInfo(){
        mainPageBeforeLogin.enterLoginAndPass(System.getProperty("US0411.login"), System.getProperty("US0411.incorrectpassword"));
    }

    @When("login button are pressed")
    public void pressLogin(){
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
    }

    @Then("the user should see logout button")
    public void seeLogout() {
        mainPageAfterLogin.verifyLogoutButton();
    }

    @Then("the user name should be displayed above the photo field")
    public void nameDisplayed() {
        mainPageAfterLogin.verifyNameDisplayed(System.getProperty("US0411.login"));
    }

    @Then("the user should see error message under field login")
    public void seeMessage(){
        mainPageAfterLogin.verifyLoginError(System.getProperty("US0411.errormessage"));
    }
}
