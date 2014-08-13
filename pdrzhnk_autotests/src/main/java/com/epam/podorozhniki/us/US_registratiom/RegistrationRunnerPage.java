package com.epam.podorozhniki.us.US_registratiom;

import java.sql.SQLException;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.core.ReadingDatafile;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.RegistrationPage;

public class RegistrationRunnerPage {

	public RegistrationRunnerPage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private ReadingDatafile rd;
	private RegistrationPage registrationPage;
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

	@Given("main page of podorozniki")
	public void givenMainPage() {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		Driver.getInstance().get(rd.baseUrl);
	}

	@Given("registration page of podorozniki")
	public void givenRegistrationPage() {
		registrationPage = new RegistrationPage();
	}

	@When("user has entered required information")
	public void enterUserInformation() {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		registrationPage = new RegistrationPage();
		registrationPage.typeLogin(rd.register_username);
		registrationPage.typePassword(rd.driver_password);
		registrationPage.confirmPassword(rd.driver_password);
		registrationPage.typephone(rd.phoneNumber);
		registrationPage.typeEmail(rd.emailString);
	}

	@When("user has clicked on the checkbox")
	public void clickCheckBox() {
		registrationPage.agreeChekButton();
	}

	@When("user has pushed confirm button")
	public void clickConfirmButton() {
		mainPageAfterLogin = registrationPage.typeSubmitButton();
	}

	@Then("user has to see error message")
	public void seeMessage() {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		registrationPage = new RegistrationPage();
		registrationPage.verifyError(rd.errorMessage_register);
	}

	@Then("user has to see user name")
	public void checkUserName() {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		mainPageAfterLogin = new MainPageAfterLogin();
		mainPageAfterLogin.verifyNameDisplayed(rd.register_username);
	}

	@Then("user has to see logout button")
	public void checkLogout() {
		mainPageAfterLogin.verifyLogoutButton();
	}

	@Then("user is deleted from database")
	public void cleaningDatabase() throws SQLException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		registrationPage = new RegistrationPage();
		registrationPage.deleteNewUserFromDB(rd.register_username);
	}

}
