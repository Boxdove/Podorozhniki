package pages;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Page {

	protected static WebDriver wdriver;

	public Page(WebDriver driver) {
		this.wdriver = driver;
	}

	public Page waitForElementFindBy (WebElement element){
	      WebDriverWait wait = new WebDriverWait(wdriver, 15, 1);
	      wait.until(ExpectedConditions.visibilityOf(element));
	      return this;
	  }
	
	public void checkText(String text, WebElement element) {
		assertEquals(element.getText(), text);
	}

	public void waitForLoad() {
		wdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
}