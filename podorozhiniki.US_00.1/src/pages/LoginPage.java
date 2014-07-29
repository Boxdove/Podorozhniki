package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Page {
	protected WebDriver wdriver;

	private static String baseUrl = "http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main";
	private static Logger log = Logger.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver) {
		super(driver);
		this.wdriver = driver;

		if (!driver.getCurrentUrl().contains(baseUrl)) {
			throw new IllegalStateException(
					"This is not the page you are expected");
		}
	}

	@FindBy(id = "inputUsername")
	private WebElement loginEdit;

	@FindBy(id = "inputPassw")
	private WebElement passwordEdit;

	@FindBy(name = "submit")
	private WebElement commitPush;

	@FindBy(xpath = "//div[@class='col-lg-3']/span")
	private WebElement username;

	public void enterLogin(String login) {
		log.info("login is performed");
		try {
			waitForElementFindBy(loginEdit);
			loginEdit.clear();
			loginEdit.sendKeys(login);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterPassword(String password) {
		try {
			waitForElementFindBy(passwordEdit);
			passwordEdit.clear();
			passwordEdit.sendKeys(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickLoginButton() {
		try {
			waitForElementFindBy(commitPush);
			commitPush.click();
			waitForElementFindBy(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MainPageService goToTheMainPAge() {
		return PageFactory.initElements(wdriver, MainPageService.class);
	}
}
