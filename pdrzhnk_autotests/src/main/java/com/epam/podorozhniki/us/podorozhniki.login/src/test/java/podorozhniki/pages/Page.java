package podorozhniki.pages;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Page {

	protected WebDriver wdriver;

	public Page(WebDriver driver) {
		this.wdriver = driver;
	}

	public Page isElementPresent(WebElement element) {
		Assert.assertTrue(element + "  should be present",
				element.isDisplayed());
		return this;
	}

	public Page isElementEnabled(WebElement element) {
		Assert.assertTrue(element + "  should be present",
				element.isDisplayed());
		return this;
	}

	public void checkText(String text, WebElement element) {
		assertEquals(element.getText(), text);
	}

	public void waitForLoad() {
		wdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
}
