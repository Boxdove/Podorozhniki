package podorozhniki.pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import podorozhniki.pages.Page;

public class LoginPage extends Page {
	protected WebDriver wdriver;

	 private static String baseUrl =
	 "http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main";

	public LoginPage(WebDriver driver) {
		super(driver);
		this.wdriver = driver;

		if (!driver.getCurrentUrl().contains(baseUrl)) {
			throw new IllegalStateException(
					"This is not the page you are expected");
		}
	}

	 @FindBy(id = "inputUsername")
	 private WebElement loginEdit;
	
	 @FindBy(id = "inputPassw")
	 private WebElement passwordEdit;
	
	 @FindBy(name = "submit")
	 private WebElement commitPush;
	
	public void enterLogin(String login) {
		assertTrue(isElementPresent(loginEdit));
		assertTrue(isElementEnabled(loginEdit));
		loginEdit.clear();
		loginEdit.sendKeys(login);
	}

	public void enterPassword(String password) {
		assertTrue(isElementPresent(passwordEdit));
		assertTrue(isElementEnabled(passwordEdit));
		passwordEdit.clear();
		passwordEdit.sendKeys(password);
	}

	public AfterLoginPage clickLoginButton() {
		assertTrue(isElementEnabled(commitPush));
		assertTrue(isElementPresent(commitPush));
		commitPush.click();
		return PageFactory.initElements(wdriver, AfterLoginPage.class);
	}

	private void assertTrue(Page elementPresent) {
	}
}
