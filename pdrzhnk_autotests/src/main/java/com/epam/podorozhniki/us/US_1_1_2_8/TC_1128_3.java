package com.epam.podorozhniki.us.US_1_1_2_8;

import static org.junit.Assert.assertFalse;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.BaseActions;
import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.core.ReadingDatafile;
import com.epam.podorozhniki.core.SeleniumRunner;

import org.junit.runner.RunWith;

/*
 * Created by Zoja_Sharova
 */

@RunWith(RerunFailedRunner.class)
public class TC_1128_3 extends BaseActions {

	// deleting trip with passenger status denied

	public TC_1128_3() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private CommonTests commonTests;
	private US_Methods us_Methods;

	public String idtr;
	public String idtr_for_delete;

	protected int numFromPageAsDriverBeforeDelet;
	protected int numFromPageAsDriverAfterDelet;
	protected int numFromBaseAsDriverBeforeDelet;
	protected int numFromBaseAsDriverAfterDelet;
	protected int numFromPageAsPassBeforeDelet;
	protected int numFromPageAsPassAfterDelet;
	protected int numFromBaseAsPassBeforeDelet;
	protected int numFromBaseAsPassAfterDelet;
	protected int deletedTripFromBase;

	@FindBy(xpath = "//li[@id='li_driver']/a")
	protected WebElement asDriverTab;

	@FindBy(xpath = "//li[@id='li_passenger']/a")
	protected WebElement asPassengerTab;

	// check driver page after deleting trip
	@Test
	public void withPassDeniedStatusAsDriverOnPage()
			throws InterruptedException, SQLException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		commonTests = new CommonTests();
		commonTests.deletingTripWithPassenger(rd.denied, asDriverTab);
		numFromPageAsDriverBeforeDelet = commonTests.numFromPageAsDriverBeforeDelet;
		numFromPageAsDriverAfterDelet = commonTests.numFromPageAsDriverAfterDelet;
		commonTests.verifyNumberOfTripsOnthePage(
				numFromPageAsDriverBeforeDelet, numFromPageAsDriverAfterDelet);
	}

	// check driver database after deleting trip
	@Test
	public void withPassDeniedStatusAsDriverInBaseAsDriver()
			throws InterruptedException, SQLException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		commonTests = new CommonTests();
		commonTests.deletingTripWithPassenger(rd.denied, asDriverTab);
		numFromBaseAsDriverBeforeDelet = commonTests.numFromBaseAsDriverBeforeDelet;
		numFromBaseAsDriverAfterDelet = commonTests.numFromBaseAsDriverAfterDelet;
		commonTests.verifyNumberOfTripsOnthePage(
				numFromBaseAsDriverBeforeDelet, numFromBaseAsDriverAfterDelet);

	}

	// check passenger page after deleting trip
	@Test
	public void withPassDeniedStatusAsPassOnPage() throws InterruptedException,
			SQLException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		commonTests = new CommonTests();
		commonTests.deletingTripWithPassenger(rd.denied, asPassengerTab);
		numFromPageAsPassBeforeDelet = commonTests.numFromPageAsPassBeforeDelet;
		numFromPageAsPassAfterDelet = commonTests.numFromPageAsPassAfterDelet;
		commonTests.verifyNumberOfTripsOnthePage(numFromPageAsPassBeforeDelet,
				numFromPageAsPassAfterDelet);
	}

	// check passenger database after deleting trip
	@Test
	public void withPassDeniedStatusAsPassInBase() throws InterruptedException,
			SQLException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		commonTests = new CommonTests();
		commonTests.deletingTripWithPassenger(rd.denied, asPassengerTab);
		numFromBaseAsPassBeforeDelet = commonTests.numFromBaseAsPassBeforeDelet;
		numFromBaseAsPassAfterDelet = commonTests.numFromBaseAsPassAfterDelet;
		commonTests.verifyNumberOfTripsOnthePage(numFromBaseAsPassBeforeDelet,
				numFromBaseAsPassAfterDelet);
	}

	// check correct deleting on the driver page
	@Test
	public void withPassDeniedStatuCorrectRemovingFromDriverTab()
			throws InterruptedException, SQLException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		us_Methods = new US_Methods();
		us_Methods.addingTrips(rd.driver_username, rd.driver_password);
		idtr = us_Methods.idtr;
		us_Methods.joinToTrip(rd.passenger_username, rd.passenger_password,
				rd.from_address, rd.to_address, idtr);
		idtr_for_delete = idtr;
		us_Methods.deletingTripAsDriver(rd.driver_username, rd.driver_password,
				idtr_for_delete);
		us_Methods.goToUserTab(rd.driver_username, rd.driver_password,
				asDriverTab);
		Assert.assertFalse(isElementPresent(By.xpath("//button[@idtr='" + idtr
				+ "']")));
	}

	// check correct deleting on the passenger page
	@Test
	public void withPassDeniedStatuCorrectRemovingFromPassTab()
			throws InterruptedException, SQLException {
		commonTests = new CommonTests();
		us_Methods = commonTests.withPassCorrectRemovingFromTab(rd.denied);
		us_Methods.goToUserTab(rd.passenger_username, rd.passenger_password,
				asPassengerTab);
		assertFalse(isElementPresent(By.xpath("//button[@idtr='" + idtr + "']")));
	}

	// check correct deleting of trip in database
	@Test
	public void withPassDeniedStatusCorrectRemovingFromDataBase()
			throws InterruptedException, SQLException {
		commonTests = new CommonTests();
		commonTests.withPassCorrectRemovingFromDataBase(rd.denied);
	}

}
