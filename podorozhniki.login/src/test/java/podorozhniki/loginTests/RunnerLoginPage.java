package podorozhniki.loginTests;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.support.PageFactory;

import podorozhniki.pageClasses.AfterLoginPage;
import podorozhniki.pageClasses.LoginPage;
import podorozhniki.pages.webdriverActions;

public class RunnerLoginPage extends webdriverActions {

	public RunnerLoginPage() {
		super();
	}

	private LoginPage loginPage;
	private AfterLoginPage afterLoginPage;

	private String username = "igor";
	private String password = "123";
	private String invalidUser = "zzzzzzzzzzz312@gmail.com";
	private String errorMessage = "Bad credentials";
	private String logoutButton = "Logout";
	private String Url = "http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main"; 

	@Given("main page http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main")
	public void givenMainPage() {
		_driver.get(Url);
		loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
		loginPage.waitForLoad();
	}

	@When("user has entered login and password into login form")
	public void enterLoginPassword() {
		loginPage.enterLogin(username);
		loginPage.enterPassword(password);
		loginPage.waitForLoad();
	}

	@When("user has entered incorrect login and password into login form")
	public void enterIncorrectLoginPassword() {
		loginPage.enterLogin(invalidUser);
		loginPage.enterPassword(password);
		loginPage.waitForLoad();
	}

	@When("user has pushed login button")
	public void pushLogin() {
		afterLoginPage = loginPage.clickLoginButton();
		afterLoginPage.waitForLoad();
	}

	@Then("user has to see logout button")
	public void logoutMessage() {
		afterLoginPage.checkLogout(logoutButton);
	}

	@Then("user has to see username")
	public void usernameDisplayed() {
		afterLoginPage.checkUsername(username);
	}

	@Then("user has to see message in the error block")
	public void checkErrorBlock() {
		afterLoginPage.checkError(errorMessage);
	}

}
