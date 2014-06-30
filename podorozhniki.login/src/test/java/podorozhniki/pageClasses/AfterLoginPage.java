package podorozhniki.pageClasses;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import podorozhniki.pages.Page;

public class AfterLoginPage extends Page {

	protected WebDriver wdriver;

	public AfterLoginPage(WebDriver driver) {
		super(driver);
		this.wdriver = driver;
	}
	
	 @FindBy(xpath = "//a[@class='btn btn-default']")
	 private WebElement logout;
	
	 @FindBy(xpath = "//div[@class='col-lg-3']/span")
	 private WebElement username;
	
	 @FindBy(className = "errorblock")
	 private WebElement error;

	public AfterLoginPage checkUsername(String userName) {
		Assert.assertTrue("user name should be present",
				username.isDisplayed());
		return this;
	}

	public AfterLoginPage checkError(String errorMessage) {
		assertEquals(error.getText(), errorMessage);
		return PageFactory.initElements(wdriver, AfterLoginPage.class);
	}

	public AfterLoginPage checkLogout(String mlogout) {
		assertEquals(logout.getText(), mlogout);
		return PageFactory.initElements(wdriver, AfterLoginPage.class);
	}
}
