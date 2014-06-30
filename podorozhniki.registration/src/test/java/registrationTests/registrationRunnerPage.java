package registrationTests;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.support.PageFactory;

import pageClasses.AfterRegistrationPage;
import pageClasses.HomePage;
import pageClasses.RegistrationPage;
import pages.webdriverActions;

public class registrationRunnerPage extends webdriverActions {

	public registrationRunnerPage() {
		super();
	}

	private RegistrationPage registrationPage;
	private AfterRegistrationPage afterRegistrationPage;
	private HomePage homePage;

	private String username = "creditnew"; // should enter new username for
											// succesful test
	private String password = "1234567";
	private String phoneNumber = "+380634444444";
	private String emailString = "absd@ukr.net";
	private String baseUrl = "http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main";
	private String errorMessage = "Sorry, but client with this login is already in use";

	@Given("main page of podorozniki")
	public void givenMainPage() {
		get(baseUrl);
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		homePage.waitForLoad();
	}

	@Given("registration page of podorozniki")
	public void givenRegistrationPage() {
		registrationPage = homePage.clickRegistrationSuggest();
		registrationPage.waitForLoad();
	}

	@When("user has entered required information")
	public void enterUserInformation() {
		registrationPage.enterLogin(username);
		registrationPage.enterPassword(password);
		registrationPage.enterConfirmation(password);
		registrationPage.enterPhone(phoneNumber);
		registrationPage.enterEmail(emailString);
		registrationPage.waitForLoad();
	}

	@When("user has clicked on the checkbox")
	public void clickCheckBox() {
		registrationPage.checkBoxClick();
	}

	@When("user has pushed confirm button")
	public void clickConfirmButton() {
		afterRegistrationPage = registrationPage.clickRegisterButton();
		afterRegistrationPage.waitForLoad();
	}

	@Then("user has to see error message")
	public void seeMessage() {
		registrationPage.checkErrorMessage(errorMessage);
	}

	@Then("user has to see user name")
	public void checkUserName() {
		afterRegistrationPage.checkUserName(username);
	}

	@Then("user has to see logout button")
	public void checkLogout() {
		afterRegistrationPage.checkLogoutButton();
	}

}
