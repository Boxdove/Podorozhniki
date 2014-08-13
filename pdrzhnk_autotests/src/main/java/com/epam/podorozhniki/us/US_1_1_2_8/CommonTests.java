package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.ErrorHandler.UnknownServerException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.DBConnection;
import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.core.ReadingDatafile;
import com.epam.podorozhniki.ui.MethodsPage;

/*
 * Created by Zoja_Sharova
 */

public class CommonTests extends MethodsPage {

	public CommonTests() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private US_Methods us_Methods;
	private ReadingDatafile rd;

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

	private static Logger log = Logger.getLogger(CommonTests.class);

	private By buttonDetails = By.xpath("//a[contains(text(),'Details')]");
	private By nextPageDriverTab = By
			.xpath("//div[@id='routeResults']//li[3]/a");
	private By buttonRemove = By.xpath("//div[@id='PassengerTrips']//td[4]/a");
	private By nextPagePassTab = By
			.xpath("//div[@id='PassengerTrips']//li[3]/a");

	@FindBy(xpath = "//li[@id='li_passenger']/a")
	protected WebElement asPassengerTab;

	@FindBy(xpath = "//li[@id='li_driver']/a")
	protected WebElement asDriverTab;

	// driver creates trips, deletes trips, counting number of trips on driver's
	// trips on page before and after deleting
	public void deletingTripWithoutPassenger() throws InterruptedException,
			SQLException, UnknownServerException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		us_Methods = new US_Methods();
		us_Methods.addingTrips(rd.driver_username, rd.driver_password);
		us_Methods.countTripsOnPage(rd.driver_username, rd.driver_password,
				buttonDetails, nextPageDriverTab, asDriverTab);
		numFromPageAsDriverBeforeDelet = us_Methods.numPage;
		log.info("Driver has " + numFromPageAsDriverBeforeDelet
				+ " trips on the page before deleting");
		numFromBaseAsDriverBeforeDelet = queryGetInt(rd.queryAsDriver);
		idtr = us_Methods.idtr;
		idtr_for_delete = idtr;
		log.info("Driver has " + numFromBaseAsDriverBeforeDelet
				+ " trips in the DB before deleting");
		us_Methods.deletingTripAsDriver(rd.driver_username, rd.driver_password,
				idtr_for_delete);
		us_Methods.countTripsOnPage(rd.driver_username, rd.driver_password,
				buttonDetails, nextPageDriverTab, asDriverTab);
		numFromPageAsDriverAfterDelet = us_Methods.numPage;
		log.info("Driver has " + numFromPageAsDriverAfterDelet
				+ " trips on the page after deleting");
		numFromBaseAsDriverAfterDelet = queryGetInt(rd.queryAsDriver);
		log.info("Driver has " + numFromBaseAsDriverAfterDelet
				+ " trips in the DB after deleting");
	}

	// driver deletes trips with passenger
	public void deletingTripWithPassenger(String pass_status,
			WebElement user_role) throws InterruptedException, SQLException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		us_Methods = new US_Methods();
		us_Methods.addingTrips(rd.driver_username, rd.driver_password);
		idtr = us_Methods.idtr;
		us_Methods.joinToTrip(rd.passenger_username, rd.passenger_password,
				rd.from_address, rd.to_address, idtr);
		idtr_for_delete = idtr;
		try {
			us_Methods.goToUserTab(rd.driver_username, rd.driver_password,
					user_role);
		} catch (Exception e) {
			log.error("Expected exception was catched");
		}
		us_Methods.goToPassStatus(rd.driver_username, rd.driver_password,
				idtr_for_delete, pass_status);
		us_Methods.countTripsOnPage(rd.driver_username, rd.driver_password,
				buttonDetails, nextPageDriverTab, asDriverTab);
		numFromPageAsDriverBeforeDelet = us_Methods.numPage;
		idtr = us_Methods.idtr;
		log.info("Driver has " + numFromPageAsDriverBeforeDelet
				+ " trips on the page before deleting, idtr = " + idtr);
		numFromBaseAsDriverBeforeDelet = queryGetInt(rd.queryAsDriver);
		log.info("Driver has " + numFromBaseAsDriverBeforeDelet
				+ " trips in the DB before deleting");
		us_Methods.countTripsOnPage(rd.passenger_username,
				rd.passenger_password, buttonRemove, nextPagePassTab,
				asPassengerTab);
		numFromPageAsPassBeforeDelet = us_Methods.numPage;
		log.info("Passenger has " + numFromPageAsPassBeforeDelet
				+ " trips on the page before deleting");
		numFromBaseAsPassBeforeDelet = queryGetInt(rd.queryAsPassenger);
		log.info("Passenger has " + numFromBaseAsPassBeforeDelet
				+ " trips in the DB before deleting");
		idtr_for_delete = idtr;
		us_Methods.deletingTripAsDriver(rd.driver_username, rd.driver_password,
				idtr_for_delete);
		us_Methods.countTripsOnPage(rd.driver_username, rd.driver_password,
				buttonDetails, nextPageDriverTab, asDriverTab);
		numFromPageAsDriverAfterDelet = us_Methods.numPage;
		log.info("Driver has " + numFromPageAsDriverAfterDelet
				+ " trips on the page after deleting");
		numFromBaseAsDriverAfterDelet = queryGetInt(rd.queryAsDriver);
		log.info("Driver has " + numFromBaseAsDriverAfterDelet
				+ " trips in the DB after deleting");
		us_Methods.countTripsOnPage(rd.passenger_username,
				rd.passenger_password, buttonRemove, nextPagePassTab,
				asPassengerTab);
		numFromPageAsPassAfterDelet = us_Methods.numPage;
		log.info("Passenger has " + numFromPageAsPassAfterDelet
				+ " trips on page after deleting");
		numFromBaseAsPassAfterDelet = queryGetInt(rd.queryAsPassenger);
		log.info("Passenger has " + numFromBaseAsPassAfterDelet
				+ " trips in the DB after deleting");
	}

	// passenger deletes trips, depends on pass_status
	public void deletingTripWithPassengerByPass(String pass_status)
			throws InterruptedException, SQLException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		us_Methods = new US_Methods();
		us_Methods.addingTrips(rd.driver_username, rd.driver_password);
		idtr = us_Methods.idtr;
		us_Methods.joinToTrip(rd.passenger_username, rd.passenger_password,
				rd.from_address, rd.to_address, idtr);
		idtr_for_delete = idtr;
		try {
			us_Methods.goToUserTab(rd.driver_username, rd.driver_password,
					asDriverTab);
		} catch (Exception e) {
			log.error("Expected exception was catched");
		}
		us_Methods.goToPassStatus(rd.driver_username, rd.driver_password,
				idtr_for_delete, pass_status);
		us_Methods.deletingTripAsPass(rd.passenger_username,
				rd.passenger_password, idtr_for_delete);
	}

	// checknig of correct deleting from page, depends on user's role
	public US_Methods withPassCorrectRemovingFromTab(String pass_status)
			throws InterruptedException, SQLException {
		log.info(" CHECKING OF CORRECT DELETING ON PASSENGER'S PAGE  GET STARTED");
		rd = new ReadingDatafile();
		rd.readingDataFile();
		us_Methods = new US_Methods();
		us_Methods.addingTrips(rd.driver_username, rd.driver_password);
		idtr = us_Methods.idtr;
		us_Methods.joinToTrip(rd.passenger_username, rd.passenger_password,
				rd.from_address, rd.to_address, idtr);
		log.info("user goToUserTab");
		us_Methods.goToUserTab(rd.driver_username, rd.driver_password,
				asDriverTab);
		log.info("user goToPassStatus");
		us_Methods.goToPassStatus(rd.driver_username, rd.driver_password,
				idtr_for_delete, pass_status);
		idtr_for_delete = idtr;
		us_Methods.deletingTripAsDriver(rd.driver_username, rd.driver_password,
				idtr_for_delete);
		return new US_Methods();

	}

	// checknig of correct deleting from db, depends on passenger status
	public void withPassCorrectRemovingFromDataBase(String pass_status)
			throws InterruptedException, SQLException {
		log.info("CHECKING OF CORRECT DELETING FROM DATABASE GET STARTED");
		rd = new ReadingDatafile();
		rd.readingDataFile();
		us_Methods = new US_Methods();
		us_Methods.addingTrips(rd.driver_username, rd.driver_password);
		idtr = us_Methods.idtr;
		log.info("user joinToTrip");
		us_Methods.joinToTrip(rd.passenger_username, rd.passenger_password,
				rd.from_address, rd.to_address, idtr);
		log.info("user goToUserTab");
		us_Methods.goToUserTab(rd.driver_username, rd.driver_password,
				asDriverTab);
		log.info("user goToPassStatus");
		us_Methods.goToPassStatus(rd.driver_username, rd.driver_password,
				idtr_for_delete, pass_status);
		log.info("user countTripsOnPage");
		idtr_for_delete = idtr;
		us_Methods.deletingTripAsDriver(rd.driver_username, rd.driver_password,
				idtr_for_delete);
		new DBConnection();
		log.info("idtr = " + idtr);
		deletedTripFromBase = queryGetInt("select * from trip where id_trip = "
				+ idtr + "");
		log.info("deletedTripFromBase =" + deletedTripFromBase);
		Assert.assertEquals("Deleted trip is present in database",
				deletedTripFromBase, 0);
		log.info("Test passed succesfully");
	}

}
