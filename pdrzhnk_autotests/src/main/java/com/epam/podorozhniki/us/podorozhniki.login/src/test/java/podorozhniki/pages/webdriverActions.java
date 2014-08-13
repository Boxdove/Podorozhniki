package podorozhniki.pages;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class webdriverActions {

	private String baseUrl;

	protected WebDriver _driver;

	@BeforeScenario
	public void setUp() throws Exception {
		_driver = new FirefoxDriver();
		baseUrl = "http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main";
	}

	public WebDriver getWebDriver() {
		return _driver;
	}

	@AfterScenario
	public void tearDown() throws Exception {
		_driver.quit();
	}

}