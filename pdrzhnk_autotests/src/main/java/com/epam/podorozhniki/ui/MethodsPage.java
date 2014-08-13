package com.epam.podorozhniki.ui;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.db.DBConnection;

public class MethodsPage {

	public int numFromPage;

	@FindBy(xpath = "//ul[@class = 'button-list']/li[2]/a")
	protected WebElement logoutButton;

	private static Logger log = Logger.getLogger(MethodsPage.class);

	public MainPageBeforeLogin logout() {
		waitForElementFindBy(logoutButton);
		logoutButton.click();
		return new MainPageBeforeLogin();
	}

	public MethodsPage waitForElementFindBy(WebElement element) {
		WebDriverWait wait = new WebDriverWait(Driver.getInstance(), 15, 1);
		wait.until(ExpectedConditions.visibilityOf(element));
		return this;
	}

	public boolean isElementPresent(By locator) {
		Driver.getInstance().manage().timeouts()
				.implicitlyWait(0, TimeUnit.SECONDS);
		List<WebElement> elements = Driver.getInstance().findElements(locator);
		Driver.getInstance().manage().timeouts()
				.implicitlyWait(30, TimeUnit.SECONDS);
		return elements.size() > 0 && elements.get(0).isDisplayed();
	}

	// Get screenshots of current page
	public void getCurrentScreenshots(String filePath, String fileName) {
		try {
			String screenPath = filePath;
			String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss")
					.format(Calendar.getInstance().getTime());
			String fileID = fileName + "_" + timeStamp;
			String screenName = String.format("%s.png", fileID);
			screenPath = screenPath + screenName;
			File screenshot = ((TakesScreenshot) Driver.getInstance())
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(screenPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Accept alert with correct alert message.
	public void checkAlert(String alertMessage) {
		try {
			WebDriverWait wait = new WebDriverWait(Driver.getInstance(), 20);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = Driver.getInstance().switchTo().alert();
			Thread.sleep(2000);
			assertTrue(alert.getText().matches(".*" + alertMessage + ".*"));
			alert.accept();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int countTripsOnPage() {
		MethodsPage method = new MethodsPage();

		int n = 1;
		int count = 0;

		while (method.isElementPresent(By
				.xpath(".//*[@id='routeResults']/div/table/tbody/tr[" + n
						+ "]/td[1]"))) {
			count++;
			n++;
		}
		return count;
	}

	public void verifyNumberOfTripsOnthePage(int numBeforeDelet,
			int numAfterDelet) {
		Assert.assertEquals("ERROR ", numBeforeDelet - 1, numAfterDelet);
	}

	public int queryGetInt(String query) throws SQLException {
		int queryGetInt = 0;
		DBConnection dbConnect = new DBConnection();
		ResultSet rs = dbConnect.queryExecutor(query);
		while (rs.next()) {
			queryGetInt = rs.getInt(1);
		}
		return queryGetInt;
	}

	// count trips on the page
	public int countTrips(By button_for_list, By nextPage) {
		List<WebElement> buttonJoins = Driver.getInstance().findElements(
				button_for_list);
		int numElem = buttonJoins.size();
		if (numElem == 0) {
			numFromPage = 0;
			log.error("There is no trip on the page");
		} else {
			numFromPage = 0;
			try {
				List<WebElement> allPages = Driver.getInstance().findElements(
						nextPage);
				int next = allPages.size();
				outer: while (next != 0) {
					buttonJoins = Driver.getInstance().findElements(
							button_for_list);
					numFromPage = numFromPage + buttonJoins.size();
					if (Driver.getInstance().findElement(nextPage).getText()
							.contains("»")) {
						break;
					} else {
						(new WebDriverWait(Driver.getInstance(), 10)).until(
								ExpectedConditions
										.visibilityOfElementLocated(nextPage))
								.click();
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						continue outer;
					}
				}
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		}
		return numFromPage;
	}

	public void catchAlert() {
		Alert alert = null;
		Wait<WebDriver> wait = new WebDriverWait(Driver.getInstance(), 3);
		try {
			alert = wait.until(ExpectedConditions.alertIsPresent());
		} catch (TimeoutException ignored) {
		}
		if (alert != null) {
			alert.accept();
		}
	}

	public boolean isElementPresent(WebElement webElement) {
		boolean exists = false;

		Driver.getInstance().manage().timeouts()
				.implicitlyWait(0, TimeUnit.SECONDS);

		try {
			webElement.getTagName();
			exists = true;
		} catch (NoSuchElementException e) {
		}

		Driver.getInstance().manage().timeouts()
				.implicitlyWait(30, TimeUnit.SECONDS);

		return exists && webElement.isDisplayed();
	}
}
