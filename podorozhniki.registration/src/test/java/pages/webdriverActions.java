package pages;

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
	}

	public void get(String baseUrl) {
		_driver.get(baseUrl);
	}

	public WebDriver getWebDriver() {
		return _driver;
	}

	@AfterScenario
	public void tearDown() throws Exception {
		_driver.quit();
	}

}