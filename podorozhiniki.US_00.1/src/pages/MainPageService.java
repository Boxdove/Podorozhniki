package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class MainPageService extends Page {

	private int numFromPage;
	private int numFromBase;
	private String DBUser;
	private String DBPassword;
	private String driver;
	private String DBUrl;

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

	public int countRowsInDataBase() {
		FileInputStream fis;
		Properties property = new Properties();

		try {
			fis = new FileInputStream("src/datas.properties");
			property.load(fis);
			try {
				fis.close();
			} catch (IOException e) {
			}

			log.info("Count the number of trips in the database");
			DBUser = property.getProperty("DBUser.id");
			DBPassword = property.getProperty("DBPassword.id");
			driver = property.getProperty("driver.id");
			DBUrl = property.getProperty("DBUrl.id");

			Connection con = null;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(DBUrl, DBUser, DBPassword);
				try {
					Statement st = con.createStatement();
					ResultSet res = st
							.executeQuery("select count(id_trip) as rowcount from trip where id_trip_status = 1");
					while (res.next()) {
						numFromBase = res.getInt(1);
					}
				} catch (SQLException s) {
					log.error("SQL statement is not executed");
				}
			} catch (Exception e) {
				log.error("Error in the connection to the database");
				e.printStackTrace();
			}
			log.info("there are " + numFromBase + "  trips in the DB");
			return numFromBase;
		} catch (IOException e) {
			log.error("Property file is absent");
		}
		return numFromBase;
	}

	public MainPageService IfNumbersAreEqual() {
		log.info("numFromBase " + numFromBase);
		log.info("numFromPage " + numFromPage);
		assertEquals("Number of trips is not correct", numFromBase, numFromPage);
		return PageFactory.initElements(wdriver, MainPageService.class);
	}

}
