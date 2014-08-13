package runner;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import pages.DBConnection;
import pages.LoginPage;
import pages.MainPageService;

public class PO {

	protected WebDriver driver;
	private LoginPage loginPage;
	private MainPageService mainPageService;
	private String baseUrl;
	private String username;
	private String password;
	private static org.apache.log4j.Logger log = Logger
			.getLogger(MainPageService.class);
	Properties property = new Properties();

	@Before
	public void setUp() throws Exception {

		FileInputStream fis;
		Properties property = new Properties();

		try {
			fis = new FileInputStream("src/datas.properties");
			property.load(fis);
			try {
				fis.close();
			} catch (IOException e) {
			}
			baseUrl = property.getProperty("baseUrl.id");
			username = property.getProperty("username.id");
			password = property.getProperty("password.id");
		} catch (IOException e) {
			log.error("Property file does not exist");
		}
		driver = new FirefoxDriver();
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}

	@Test
	public void listOfRoutesOnMainPage() throws SQLException {
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.enterLogin(username);
		loginPage.enterPassword(password);
		loginPage.waitForLoad();
		loginPage.clickLoginButton();
		mainPageService = loginPage.goToTheMainPAge();
		mainPageService.countRowsOnThePage();
		mainPageService.countTripsInDatabase();
		mainPageService.IfNumbersAreEqual();
	}

	@After
	public void tearDown() throws Exception {
		driver.close();
		driver.quit();
	}
}
