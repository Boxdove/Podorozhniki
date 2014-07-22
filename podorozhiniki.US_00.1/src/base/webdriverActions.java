package base;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class webdriverActions {

	private String baseUrl;

	protected WebDriver _driver;

	@Before
	public void setUp() throws Exception {
		_driver = new FirefoxDriver();
		baseUrl = "http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main";
	}

	public WebDriver getWebDriver() {
		return _driver;
	}

	@After
	public void tearDown() throws Exception {
		_driver.close();
	}

}