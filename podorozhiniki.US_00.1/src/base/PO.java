package base;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import pages.*;

public class PO {

	protected WebDriver driver;
	private String baseUrl = "http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main";

	private LoginPage loginPage;
	private DatabaseService databaseService;

	private String username = "creditnew";
	private String password = "1234567";
	private String errorMessage = "Bad credentials";

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}

	@Test
	public void listOfRoutesOnMainPage() {
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		try {
			loginPage.enterLogin(username);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			loginPage.enterPassword(password);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		loginPage.waitForLoad();
		databaseService = loginPage.clickLoginButton();
		try {
			databaseService.countRowsOnThePage();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		databaseService.countRowsInDataBase();
//		databaseService.IfNumbersAreEqual();
	}

	@After
	public void tearDown() throws Exception {
		driver.close();
	}
}
