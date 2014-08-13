package pageClasses;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.Page;

public class RegistrationPage extends Page {
	protected WebDriver wdriver;

	public RegistrationPage(WebDriver driver) {
		super(driver);
		this.wdriver = driver;
	}

	@FindBy(id = "login")
	private WebElement loginEnter;

	@FindBy(id = "password")
	private WebElement passwordEnter;

	@FindBy(name = "confirmPassword")
	private WebElement confirmEnter;

	@FindBy(name = "phone")
	private WebElement phone;

	@FindBy(name = "email")
	private WebElement email;

	@FindBy(name = "agree")
	private WebElement checkBoxAgree;

	@FindBy(id = "b")
	private WebElement registerButton;

	@FindBy(xpath = "//span[@id='login.errors']")
	private WebElement errorBlock;

	public void enterLogin(String login) {
		assertTrue(isElementPresent(loginEnter));
		assertTrue(isElementEnabled(loginEnter));
		loginEnter.clear();
		loginEnter.sendKeys(login);
	}

	public void enterPassword(String password) {
		assertTrue(isElementPresent(passwordEnter));
		assertTrue(isElementEnabled(passwordEnter));
		passwordEnter.clear();
		passwordEnter.sendKeys(password);
	}

	public void enterConfirmation(String password) {
		assertTrue(isElementPresent(confirmEnter));
		assertTrue(isElementEnabled(confirmEnter));
		confirmEnter.clear();
		confirmEnter.sendKeys(password);
	}

	public void enterPhone(String phoneNumber) {
		assertTrue(isElementPresent(phone));
		assertTrue(isElementEnabled(phone));
		phone.clear();
		phone.sendKeys(phoneNumber);
	}

	public void enterEmail(String emailString) {
		assertTrue(isElementPresent(email));
		assertTrue(isElementEnabled(email));
		email.clear();
		email.sendKeys(emailString);
	}

	public void checkBoxClick() {
		assertTrue(isElementPresent(checkBoxAgree));
		assertTrue(isElementEnabled(checkBoxAgree));
		checkBoxAgree.click();
	}

	public AfterRegistrationPage clickRegisterButton() {
		assertTrue(isElementPresent(registerButton));
		registerButton.click();
		return PageFactory.initElements(wdriver, AfterRegistrationPage.class);
	}

	public void checkErrorMessage(String errorMessage) {
		assertEquals(errorBlock.getText(), errorMessage);
	}

	private void assertTrue(Page elementPresent) {

	}

}