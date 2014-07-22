//package userstory.base;
//
//import org.jbehave.core.annotations.Given;
//
//import static org.junit.Assert.assertEquals;
//
//import org.jbehave.core.annotations.Then;
//import org.jbehave.core.annotations.When;
//import org.openqa.selenium.support.PageFactory;
//
//import userstory.pages.LoginPage;
//import userstory.pages.DatabaseService;
//
//public class RunnerSteps extends webdriverActions {
//
//	public RunnerSteps() {
//		super();
//	}
//
//	private LoginPage loginPage;
//	private DatabaseService databaseService;
//	
//	private String username = "creditnew";
//	private String password = "1234567";
//	private String errorMessage = "Bad credentials";
//	
//	private String Url = "http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main";
//
//	@Given("main page http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main")
//	public void givenMainPage() {
//		_driver.get(Url);
//		loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
//		loginPage.waitForLoad();
//	}
//
//	@When("user logs into system")
//	public void enterLoginPassword() {
//		loginPage.enterLogin(username);
//		loginPage.enterPassword(password);
//		loginPage.waitForLoad();
//		databaseService = loginPage.clickLoginButton();
//	}
//
//	@Then("user should see valid list of routes")
//	public void numberOfRoutes() {
//		try {
//			databaseService.countRowsOnThePage();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		databaseService.countRowsInDataBase();
//		databaseService.IfNumbersAreEqual();
//	}
//}
