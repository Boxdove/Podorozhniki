package com.epam.podorozhniki.ui;

import com.epam.podorozhniki.core.Driver;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertTrue;

/**
 * Created by Yana_Velbovets on 6/27/2014.
 */
public class MainPageBeforeLogin {

	@FindBy(id = "inputUsername")
	private WebElement UsernameField;

	@FindBy(id = "inputPassw")
	private WebElement PassField;

	@FindBy(xpath = "//button[@name='submit']")
	private WebElement SubmitBtn;

	@FindBy(xpath = "//a[@class='btn btn-default']")
	private WebElement logoutButton;

	@FindBy(xpath = "//a[contains(@href, 'registr')]")
	private WebElement registrationLink;

	public MainPageBeforeLogin() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	public void assertFieldsArePresent() {
		assertTrue(UsernameField.isDisplayed());
		assertTrue(PassField.isDisplayed());
		assertTrue(SubmitBtn.isDisplayed());
	}

	public void enterLoginAndPass(String login, String password) {
		UsernameField.clear();
		UsernameField.sendKeys(login);
		PassField.clear();
		PassField.sendKeys(password);
	}

	public MainPageAfterLogin pressTheLoginButton() {
		SubmitBtn.click();
		return new MainPageAfterLogin();
	}

	public RegistrationPage regLink() {
		assertTrue(registrationLink.isDisplayed());
		registrationLink.click();
		return new RegistrationPage();
	}
}
