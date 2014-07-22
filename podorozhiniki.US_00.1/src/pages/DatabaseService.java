package pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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

public class DatabaseService extends Page {

	private int numFromPage;
	private int numFromBase;
	private int numOnSinglePage;

	private By buttonJoin = By.xpath("//button[contains(text(),'Join')]");
	private By nextPage = By
			.xpath("//li[@class='active']/following-sibling::*[1]/self::li/a");

	public DatabaseService(WebDriver driver) {
		super(driver);
	}

	public DatabaseService countRowsOnThePage() throws InterruptedException {
		List<WebElement> buttonJoins = wdriver.findElements(buttonJoin);
		int numElem = buttonJoins.size();
		if (numElem == 0) {
			// РµСЃР»Рё РјР°СЂС€СЂСѓС‚РѕРІ РЅРµС‚
			numFromPage = 0;
			System.out.println("there is no routes");
		} else {
			System.out.println("I found routes");
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
						Thread.sleep(5000);
						continue outer;
					}
				}
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
			System.out.println(numFromPage);
		}
		return PageFactory.initElements(wdriver, DatabaseService.class);
	}

	public int countRowsInDataBase() {

		String user = "postgres";
		String password = "123";
		String url = "jjdbc:postgresql://evuakyisd0235.kyiv.epam.com/Podorozhniki";

		// String driver = "org.postgresql.Driver";// Р�РјСЏ РґСЂР°Р№РІРµСЂР°

		try {
			Class.forName("org.postgresql.Driver");// Р РµРіРёСЃС‚СЂРёСЂСѓРµРј
													// РґСЂР°Р№РІРµСЂ
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection c = null; // РЎРѕРµРґРёРЅРµРЅРёРµ СЃ Р‘Р”
		int count = 0;
		try {
			c = DriverManager.getConnection(url, user, password);// РЈСЃС‚Р°РЅРѕРІРєР°
																	// СЃРѕРµРґРёРЅРµРЅРёСЏ
																	// СЃ Р‘Р”
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select count(id_route) from route");// Р·Р°РїСЂРѕСЃ

			numFromBase = ((Number) rs.getObject(1)).intValue();
			System.out.println(numFromBase);

			while (rs.next()) {
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Р·Р°РєСЂС‹РІРІРµРј СЃРѕРµРґРёРЅРµРЅРёРµ
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return numFromBase;
	}

	public DatabaseService IfNumbersAreEqual() {
		assertEquals("NUmber of routes is not correct", numFromBase,
				numFromPage);
		return PageFactory.initElements(wdriver, DatabaseService.class);
	}

}
