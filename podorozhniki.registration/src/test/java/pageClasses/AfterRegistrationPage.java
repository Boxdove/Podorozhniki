package pageClasses;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.Page;

public class AfterRegistrationPage extends Page {

	protected WebDriver wdriver;

	public AfterRegistrationPage(WebDriver driver) {
		super(driver);
		this.wdriver = driver;
	}

	@FindBy(xpath = "//a[@class='btn btn-default']")
	private WebElement logout;

	@FindBy(xpath = "//div[@class='col-lg-3']/span")
	private WebElement username;

	public AfterRegistrationPage checkUserName(String myUsername) {
		assertEquals(username.getText(), myUsername);
		return PageFactory.initElements(wdriver, AfterRegistrationPage.class);
	}

	public void checkLogoutButton() {
		assertTrue(isElementPresent(logout));
	}

	private void assertTrue(Page elementPresent) {
	}

	public AfterRegistrationPage init() {
		return PageFactory.initElements(wdriver, AfterRegistrationPage.class);
	}
}
