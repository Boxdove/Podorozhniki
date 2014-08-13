package pageClasses;

//import static org.junit.Assert.assertTrue;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//
//import pages.Page;
//
//public class HomePage extends Page {
//	protected WebDriver wdriver;
//	private static String baseUrl = "http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main";
//
//	public HomePage(WebDriver driver) {
//		super(driver);
//		this.wdriver = driver;
//
//		if (!driver.getCurrentUrl().contains(baseUrl)) {
//			throw new IllegalStateException(
//					"This is not the page you are expected");
//		}
//	}
//
//	@FindBy(xpath = "//fieldset/div[2]/div[1]/p/a")
//	private WebElement registrationSuggest;
//
//	public RegistrationPage clickRegistrationSuggest() {
//		assertTrue(isElementPresent(registrationSuggest));
//		registrationSuggest.click(); 
//		return PageFactory.initElements(wdriver, RegistrationPage.class); 
//		
//	}
//	
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.Page;

public class HomePage extends Page {

	private static String baseUrl = "http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main";

	public HomePage(WebDriver driver) {
		super(driver);
		if (!driver.getCurrentUrl().contains(baseUrl)) {
			throw new IllegalStateException(
					"This is not the page you are expected");
		}

	}

	@FindBy(xpath = "//form/fieldset/div[2]/div[1]/p/a")
	private WebElement registrationlink;

	public RegistrationPage clickRegistrationSuggest() {
		assertTrue(isElementPresent(registrationlink));
		registrationlink.click();
		return PageFactory.initElements(wdriver, RegistrationPage.class);
	}

	private void assertTrue(Page elementPresent) {
		// TODO Auto-generated method stub

	}

}
