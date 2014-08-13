package com.epam.podorozhniki.ui;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.db.DBConnection;
import com.epam.podorozhniki.us.US_1_1_2_8.TC_1128_1;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Viktoriia_Ishchuk on 7/25/2014.
 * 
 * @param <MyTrips>
 */
public class MyTripsPage<MyTrips> extends MethodsPage {
	public MyTripsPage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private static Logger log = Logger.getLogger(TC_1128_1.class);

	@FindBy(xpath = "//div[@class='logo-container']/a")
	public WebElement mainPageLink;

	@FindBy(css = ".btn.btn-default")
	public WebElement logout;

	@FindBy(id = "my_trips")
	public WebElement myTripsLink;

	// Tabs
	@FindBy(xpath = "//li[@id='li_passenger']/a")
	protected WebElement asPassengerTab;

	@FindBy(xpath = "//li[@id='li_driver']/a")
	protected WebElement asDriverTab;

	@FindBy(xpath = "//li[@id='li_driverCalendar']/a")
	protected WebElement driverCalendarTab;

	@FindBy(xpath = "//li[@id='li_passengerCalendar']/a")
	protected WebElement passengerCalendarTab;

	// As Passenger Tab
	@FindBy(xpath = "//div[@id='PassengerTrips']/div/table/tbody/tr/td[5]")
	private WebElement asPassengerStatusSubmitted;

	// As Driver Tab
	@FindBy(xpath = "//td[@id='actual_status0']")
	private WebElement asPasStatusSubmittedOnDriverPage;
	
	@FindBy(xpath = "//div[@class ='control-group span8']/a")
	protected WebElement asDriverAddTripButton;

	@FindBy(xpath = "//tr[1]//a[contains(text(),'Details')]")
	protected WebElement asDriverDetailsButton;

	@FindBy(id = "accept0")
	protected WebElement asDriverAcceptedButton;

	@FindBy(id = "deny0")
	protected WebElement asDriverDeniedButton;

	@FindBy(xpath = "//input[@value='Confirm']")
	protected WebElement asDriverConfirmButton;

	@FindBy(xpath = "//tr[1]//a[contains(text(),'Remove')]")
	protected WebElement asDriverRemoveLink;

	@FindBy(xpath = "//div[@id='removeModalWithPassengers']//button[contains(text(),'Remove')]")
	protected WebElement asDriverRemoveWithPassengersButton;

	@FindBy(xpath = "//div[@id='removeModalWithoutPassengers']//button[contains(text(),'Remove')]")
	protected WebElement asDriverRemoveWithOutPassengersButton;

	@FindBy(xpath = "//button[contains(text(),'Yes')]")
	protected WebElement asPassRemoveConfirmButton;

	@FindBy(xpath = "//div[@id='routeResults']//td[1]/a")
	protected WebElement fromTripLink;

	@FindBy(xpath = "//td[5]")
	private WebElement SeatsTaken;

	@FindBy(xpath = "//td[4]")
	private WebElement SeatsTotal;

	@FindBy(xpath = "//td[6]")
	private WebElement SeatsLeft;

	// Driver Calendar
	@FindBy(xpath = "//div[@id='driverCalendar']//button[@ data-calendar-nav='prev']")
	protected WebElement driverCalendarPrevButton;

	@FindBy(xpath = "//div[@id='driverCalendar']//button[@ data-calendar-nav='next']")
	protected WebElement driverCalendarNextButton;

	@FindBy(xpath = "//div[@id = 'driverCalendar']//button[@data-calendar-view = 'day']")
	protected WebElement driverCalendarDayFilter;

	@FindBy(xpath = "//div[@id = 'driverCalendar']//button[@data-calendar-view = 'week']")
	protected WebElement driverCalendarWeekFilter;

	@FindBy(xpath = "//div[@id = 'driverCalendar']//button[@data-calendar-view = 'month']")
	protected WebElement driverCalendarMonthFilter;

	@FindBy(xpath = "//div[@id = 'driverCalendar']//button[@data-calendar-view = 'year']")
	protected WebElement driverCalendarYearFilter;

	// Passenger Calendar
	@FindBy(xpath = "//div[@id='passengerCalendar']//button[@ data-calendar-nav='prev']")
	protected WebElement passengerCalendarPrevButton;

	@FindBy(xpath = "//div[@id='passengerCalendar']//button[@ data-calendar-nav='next']")
	protected WebElement passengerCalendarNextButton;

	@FindBy(xpath = "//div[@id = 'passengerCalendar']//button[@data-calendar-view = 'day']")
	protected WebElement passengerCalendarDayFilter;

	@FindBy(xpath = "//div[@id = 'passengerCalendar']//button[@data-calendar-view = 'week']")
	protected WebElement passengerCalendarWeekFilter;

	@FindBy(xpath = "//div[@id = 'passengerCalendar']//button[@data-calendar-view = 'month']")
	protected WebElement passengerCalendarMonthFilter;

	@FindBy(xpath = "//div[@id = 'passengerCalendar']//button[@data-calendar-view = 'year']")
	protected WebElement passengerCalendarYearFilter;

	@FindBy(xpath = "//div[@class = 'pull-left day-event day-highlight dh-event-important']")
	protected WebElement passengerCalendarDayMessage;

	@FindBy(xpath = "//div[@data-event-class='event-important']")
	protected WebElement passengerCalendarWeekMessage;

	@FindBy(xpath = "//div[@class='events-list']")
	protected WebElement passengerCalendarMonthMessage;

	@FindBy(xpath = "//a[@class='event-item']")
	protected WebElement passengerCalendarMonthItem;

	@FindBy(xpath = "//div[@id ='calendarPassender']/div/div[2]/div[3]")
	protected WebElement passengerCalendarYearMessage;

	@FindBy(xpath = "//a[@class='event-item']")
	protected WebElement passengerCalendarYearItem;

	// Filters on Driver's Calendar Page

	@FindBy(id = "from_id")
	protected WebElement locatorForFromFilter;

	@FindBy(id = "to_id")
	protected WebElement locatorForToFilter;

	@FindBy(id = "trip_status_id")
	protected WebElement locatorForTripStatusFilter;

	@FindBy(id = "total_seats_id")
	protected WebElement locatorForSeatsTotalFilter;

	@FindBy(id = "occupied_seats")
	protected WebElement locatorForSeatsOccupiedFilter;

	@FindBy(id = "free_seats")
	protected WebElement locatorForSeatsFreeFilter;

	@FindBy(id = "requests")
	protected WebElement locatorForRequestsFilter;

	// Methods
	public MainPageAfterLogin gotoMainPage() {
		mainPageLink.click();
		return new MainPageAfterLogin();
	}

	public void gotoMyTripsMainPage() {
		myTripsLink.click();
	}

	public void gotoMyDetailsAsDriver() {
		asDriverDetailsButton.click();
	}
	
	public void gotoAsDriverTab() {
		asDriverTab.click();
	}

	public void gotoAsPassengerTab() {
		asPassengerTab.click();
	}

	public void gotoRoleTab(WebElement user_role) {
		try {
			user_role.click();
		} catch (Exception e) {
			log.error("Expected exception");
		}
	}

	public void gotoDriverCalendar() {
		driverCalendarTab.click();
	}

	public void gotoPassengerCalendar() {
		waitForElementFindBy(passengerCalendarTab);
		passengerCalendarTab.click();
	}

	public AddTripPage gotoAddTripPage() {
		asDriverTab.click();
		asDriverAddTripButton.click();
		return new AddTripPage();
	}

	public void acceptPassengerTrip() {
		asDriverTab.click();
		asDriverDetailsButton.click();
		asDriverAcceptedButton.click();
		asDriverConfirmButton.click();
	}

	public void rejectPassengerTrip() {
		asDriverTab.click();
		asDriverDetailsButton.click();
		asDriverDeniedButton.click();
		asDriverConfirmButton.click();
	}

	public void setStatusToPassengerTrip(String pass_status) {
		asDriverTab.click();
		waitForElementFindBy(asDriverDetailsButton);
		asDriverDetailsButton.click();
		log.info("Setting status ");
		try {
			new WebDriverWait(Driver.getInstance(), 10, 1)
					.until(ExpectedConditions.elementToBeClickable(By
							.id(pass_status))).click();
			Thread.sleep(1000);
			asDriverConfirmButton.click();
			log.info("Status was clicked ");
		} catch (Exception e) {
			log.error("Expected exception ");
		}
	}
	
	

	public void acceptPassengerTrip(String idtr) {
		asDriverTab.click();
		Driver.getInstance()
				.findElement(
						By.xpath("//a[@href='javascript:getPassengersCountForJoining("
								+ idtr + ")']")).click();
		asDriverAcceptedButton.click();
		asDriverConfirmButton.click();
	}

	public void rejectPassengerTrip(String idtr) {
		asDriverTab.click();
		Driver.getInstance()
				.findElement(
						By.xpath("//a[@href='javascript:getPassengersCountForJoining("
								+ idtr + ")']")).click();
		asDriverDeniedButton.click();
		asDriverConfirmButton.click();
	}

	public void removePassengerTrip() {
		asDriverTab.click();
		asDriverRemoveLink.click();
		try {
			asDriverRemoveWithPassengersButton.click();
		} catch (Exception e1) {
		}
		try {
			asDriverRemoveWithOutPassengersButton.click();
		} catch (Exception e2) {
		}
	}

	public void removeSpecificTripAsDriver(String idtr) {
		asDriverTab.click();
		asDriverRemoveLink = Driver.getInstance().findElement(
				By.xpath("//a[@href = 'javascript:checkIsTripHasPassenger("
						+ idtr + ")']"));
		asDriverRemoveLink.click();
		try {
			waitForElementFindBy(asDriverRemoveWithPassengersButton);
			asDriverRemoveWithPassengersButton.click();
		} catch (TimeoutException e1) {
			log.error("Expected exception: asDriverRemoveWithPassengersButton was not found");
		}
		try {
			waitForElementFindBy(asDriverRemoveWithOutPassengersButton);
			asDriverRemoveWithOutPassengersButton.click();
		} catch (TimeoutException e2) {
			log.error("Expected exception:  asDriverRemoveWithoutPassengersButton was not found");
		}
		catchAlert();
	}

	public void removeSpecificTripAsPass(String idtr) {
		asPassengerTab.click();
		asDriverRemoveLink.click();
		asPassRemoveConfirmButton.click();
		catchAlert();
	}

	public boolean amountOfSeatsLeft(String ammOfSeats) {
		int totalLeft = 0;
		int actualAmountOfSeatsTaken = Integer.valueOf(ammOfSeats);
		int seatsTotal = Integer.valueOf(SeatsTotal.getText());
		totalLeft = seatsTotal - actualAmountOfSeatsTaken;
		if (totalLeft == Integer.valueOf(SeatsLeft.getText())) {
			return true;
		} else {
			return false;
		}
	}

	public int curentAmountOfSeatsLeft() {
		return Integer.valueOf(SeatsTaken.getText());
	}

	
	public void statusIsSubmitted(String verification) {
		assertEquals(verification, getStatus());
	}

	public String getStatus() {
		return asPassengerStatusSubmitted.getText().toLowerCase();
	}
	
	public void statusIsSubmittedOnDriverPage(String verification) {
		assertEquals(verification, getStatusFromDriverPage());
	}

	public String getStatusFromDriverPage() {
		return asPasStatusSubmittedOnDriverPage.getText().toLowerCase();
	}

	public String getTripId() {
		String idtr = "";
		idtr = fromTripLink.getAttribute("href");
		idtr = idtr.substring(21, 25);
		return idtr;
	}

	public void verifyTripStatusAsPassengerByDayFilter(String verifyWord) {
		getCurrentScreenshots(
				"D:\\Viktoriia_Ishchuk\\gitProjects\\podorozhniki_us11\\screenshots\\",
				"verifyDay");
		assertTrue(passengerCalendarDayMessage.getText().matches(
				".*" + verifyWord + ".*"));
	}

	public void verifyTripStatusAsPassengerByWeekFilter(String verifyWord) {
		passengerCalendarWeekFilter.click();
		getCurrentScreenshots(
				"D:\\Viktoriia_Ishchuk\\\\gitProjects\\\\podorozhniki_us11\\\\screenshots\\",
				"verifyWeek");
		assertTrue(passengerCalendarWeekMessage.getText().matches(
				".*" + verifyWord + ".*"));
	}

	public void verifyTripStatusAsPassengerByMonthFilter(String verifyWord) {
		passengerCalendarMonthFilter.click();
		passengerCalendarMonthMessage.click();
		assertTrue(passengerCalendarMonthItem.getText().matches(
				".*" + verifyWord + ".*"));
		getCurrentScreenshots(
				"D:\\Viktoriia_Ishchuk\\gitProjects\\podorozhniki_us11\\screenshots\\",
				"verifyMonth");
	}

	public void verifyTripStatusAsPassengerByYearFilter(String verifyWord) {
		passengerCalendarYearFilter.click();
		passengerCalendarYearMessage.click();
		assertTrue(passengerCalendarYearItem.getText().matches(
				".*" + verifyWord + ".*"));
		getCurrentScreenshots(
				"D:\\Viktoriia_Ishchuk\\gitProjects\\podorozhniki_us11\\screenshots\\",
				"verifyYear");
	}

	// Methods for US01_4_2
	public boolean isFilterFromPresent() {
		return isElementPresent(locatorForFromFilter);
	}

	public boolean isFilterToPresent() {
		return isElementPresent(locatorForToFilter);
	}

	public boolean isFilterTripStatusPresent() {
		return isElementPresent(locatorForTripStatusFilter);
	}

	public boolean isFilterSeatsTotalPresent() {
		return isElementPresent(locatorForSeatsTotalFilter);
	}

	public boolean isFilterSeatsOccupiedPresent() {
		return isElementPresent(locatorForSeatsOccupiedFilter);
	}

	public boolean isFilterSeatsFreePresent() {
		return isElementPresent(locatorForSeatsFreeFilter);
	}

	public boolean isFilterRequestsPresent() {
		return isElementPresent(locatorForRequestsFilter);
	}

	public void particularSelectionInFilter(WebElement element) {
		Select select = new Select(element);
		// selecting option in Drop-down using Index
		select.selectByIndex(1);
	}

	public void particularSelectionInFromFilter() {
		particularSelectionInFilter(locatorForFromFilter);
	}

	public void particularSelectionInToFilter() {
		particularSelectionInFilter(locatorForToFilter);
	}

	public void particularSelectionInTripStatusFilter() {
		particularSelectionInFilter(locatorForTripStatusFilter);
	}

	public void particularSelectionInSeatsTotalFilter() {
		particularSelectionInFilter(locatorForSeatsTotalFilter);
	}

	public void particularSelectionInSeatsOccupiedFilter() {
		particularSelectionInFilter(locatorForSeatsOccupiedFilter);
	}

	public void particularSelectionInSeatsFreeFilter() {
		particularSelectionInFilter(locatorForSeatsFreeFilter);
	}

	public void particularSelectionInRequestsFilter() {
		particularSelectionInFilter(locatorForRequestsFilter);
	}

	public void selectAllInFilter(WebElement element) {
		Select select = new Select(element);
		// selecting option in Drop-down using Index
		select.selectByIndex(0);
	}

	public void selectAllInFromFilter() {
		selectAllInFilter(locatorForFromFilter);
	}

	public void selectAllInToFilter() {
		selectAllInFilter(locatorForToFilter);
	}

	public void selectAllInTripStatusFilter() {
		selectAllInFilter(locatorForTripStatusFilter);
	}

	public void selectAllInSeatsTotalFilter() {
		selectAllInFilter(locatorForSeatsTotalFilter);
	}

	public void selectAllInSeatsOccupiedFilter() {
		selectAllInFilter(locatorForSeatsOccupiedFilter);
	}

	public void selectAllInSeatsFreeFilter() {
		selectAllInFilter(locatorForSeatsFreeFilter);
	}

	public void selectAllInRequestsFilter() {
		selectAllInFilter(locatorForRequestsFilter);
	}

	public int countTripsInColume(int num) {
		MethodsPage method = new MethodsPage();

		int n = 1;
		int count = 0;

		while (method.isElementPresent(By
				.xpath(".//*[@id='routeResults']/div/table/tbody/tr[" + n
						+ "]/td[" + num + "]"))) {
			count++;
			n++;
		}
		return count;
	}

	public int countRecordsInColumnFromDb(String query) throws SQLException {
		DBConnection db = new DBConnection();
		String countTrips = "select count(*)as qty" + " from point"
				+ " where name = '" + query + "'";
		ResultSet expectedResult = db.queryExecutor(countTrips);
		expectedResult.next();

		int count = expectedResult.getInt(1);

		return count;
	}

}
