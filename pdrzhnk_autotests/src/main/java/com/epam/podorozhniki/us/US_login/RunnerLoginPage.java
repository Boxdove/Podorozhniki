package com.epam.podorozhniki.us.US_login;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.core.ReadingDatafile;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;

public class RunnerLoginPage {

	public RunnerLoginPage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private ReadingDatafile rd;
	private MainPageBeforeLogin mainPageBeforeLogin;
	private MainPageAfterLogin mainPageAfterLogin;

	@BeforeScenario
	public void setUp() {
		Driver.init();
		Driver.getInstance().manage().window().maximize();
	}

	@AfterScenario
	public void tearDown() {
		Driver.tearDown();
	}

	@Given("main page http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main")
	public void givenMainPage() {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		Driver.getInstance().get(rd.baseUrl);
	}

	@When("user has entered login and password into login form")
	public void enterLoginPassword() {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		System.out.println("DATAS: " + rd.driver_password + rd.driver_username);
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(rd.driver_username,
				rd.driver_password);
	}

	@When("user has entered incorrect login and password into login form")
	public void enterIncorrectLoginPassword() {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(rd.invalidUser,
				rd.driver_password);
	}

	@When("user has pushed login button")
	public void pushLogin() {
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
	}

	@Then("user has to see logout button")
	public void logoutMessage() {
		mainPageAfterLogin.verifyLogoutButton();
	}

	@Then("user has to see username")
	public void usernameDisplayed() {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageAfterLogin.verifyNameDisplayed(rd.driver_username);
	}

	@Then("user has to see message in the error block")
	public void checkErrorBlock() {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageAfterLogin.verifyLoginError(rd.errorMessage_login);
	}

}
