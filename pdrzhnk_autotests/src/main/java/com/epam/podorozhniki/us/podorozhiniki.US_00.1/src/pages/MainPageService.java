package pages;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageService extends Page {

	protected int numFromPage;
	protected int numFromBase;
	protected String DBUser;
	protected String DBPassword;
	protected static String driverName;
	public static String driver;
	protected String DBUrl;
	protected static String query;
	protected DBConnection dbConnect;
	protected static ReadingDataFile readingProperty; 

	private By buttonJoin = By.xpath("//button[contains(text(),'Join')]");
	private By nextPage = By
			.xpath("//li[@class='active']/following-sibling::*[1]/self::li/a");
	private static org.apache.log4j.Logger log = Logger
			.getLogger(MainPageService.class);

	public MainPageService(WebDriver driver) {
		super(driver);
	}

	public int countRowsOnThePage() {
		List<WebElement> buttonJoins = wdriver.findElements(buttonJoin);
		int numElem = buttonJoins.size();
		if (numElem == 0) {
			numFromPage = 0;
			log.error("Preconditions are wrong: there is no trip");
		} else {
			numFromPage = 0;
			try {
				List<WebElement> allPages = wdriver.findElements(nextPage);
				int next = allPages.size();
				outer: while (next != 0) {
					buttonJoins = wdriver.findElements(buttonJoin);
					numFromPage = numFromPage + buttonJoins.size();
					if (wdriver.findElement(nextPage).getText().contains("»")) {
						break;
					} else {
						(new WebDriverWait(wdriver, 10)).until(
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
		log.info("there are " + numFromPage + " trips on the main page");
		return numFromPage;
	}
	
	public MainPageService countTripsInDatabase() throws SQLException {
		log.info("countRowsInDataBase start");
		readingProperty = PageFactory.initElements(wdriver, ReadingDataFile.class);
		readingProperty.readingProperty(); 
		DBUser = System.getProperty("DBUser.id");
		DBPassword = System.getProperty("DBPassword.id");
		driver = System.getProperty("driver.id");
		DBUrl = System.getProperty("DBUrl.id");
		query = System.getProperty("query.id");
		dbConnect = PageFactory.initElements(wdriver, DBConnection.class);
		ResultSet rs = dbConnect.queryExecutor(query);
		while (rs.next()) {
			numFromBase = rs.getInt(1);
		}
		log.info("there are " + numFromBase + " trips in the DB");
		return PageFactory.initElements(wdriver, MainPageService.class);
	}

	public MainPageService IfNumbersAreEqual() throws SQLException {
		log.info("numFromBase"+numFromBase);
		log.info("numFromPage"+numFromPage);
		assertEquals("Number of trips is not correct", numFromBase, numFromPage);
		return PageFactory.initElements(wdriver, MainPageService.class);
	}

}
