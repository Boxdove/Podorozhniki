package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.AddTripPage;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;
import com.epam.podorozhniki.ui.MethodsPage;
import com.epam.podorozhniki.ui.MyTripsPage;

/*
 * Created by Zoja_Sharova
 */
public class US_Methods extends MethodsPage {
	public US_Methods() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	@FindBy(xpath = "//li[@id='li_driver']/a")
	protected WebElement asDriverTab;

	@FindBy(xpath = "//li[@id='li_passenger']/a")
	protected WebElement asPassengerTab;

	private MainPageAfterLogin mainPageAfterLogin;
	private MainPageBeforeLogin mainPageBeforeLogin;

	@SuppressWarnings("rawtypes")
	private MyTripsPage myTripsPage;
	private AddTripPage addTripPage;
	private ReadingDatafile rd;

	public String idtr;

	public int numPage;

	private static Logger log = Logger.getLogger(US_Methods.class);

	// driver adds 3 trips
	public void addingTrips(String username, String password)
			throws InterruptedException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		log.info("Driver is adding the trips");
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(username, password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		addTripPage = myTripsPage.gotoAddTripPage();
		myTripsPage = addTripPage.addTrip(rd.from_address, rd.to_address,
				rd.alert);
		myTripsPage.catchAlert();
		idtr = myTripsPage.getTripId();
//		addTripPage = myTripsPage.gotoAddTripPage();
//		myTripsPage = addTripPage.addTrip(rd.from_address_1, rd.to_address_1,
//				rd.alert);
//		myTripsPage.catchAlert();
//		addTripPage = myTripsPage.gotoAddTripPage();
//		myTripsPage = addTripPage.addTrip(rd.from_address_2, rd.to_address_2,
//				rd.alert);
//		myTripsPage.catchAlert();
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageAfterLogin.logout();
	}

	// driver deletes specific trip
	public void deletingTripAsDriver(String username, String password,
			String idtr_for_delete) throws SQLException, InterruptedException {
		log.info("Driver is deleting specific trip");
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(username, password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		myTripsPage.removeSpecificTripAsDriver(idtr_for_delete);
		myTripsPage.catchAlert();
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageAfterLogin.logout();
	}

	// driver deletes specific trip
	public void deletingTripAsPass(String username, String password,
			String idtr_for_delete) throws SQLException, InterruptedException {
		log.info("Passenger is deleting specific trip");
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(username, password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		myTripsPage.removeSpecificTripAsPass(idtr_for_delete);
		myTripsPage.catchAlert();
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageAfterLogin.logout();
	}

	// user counts trips on page depends on user's role
	public void countTripsOnPage(String username, String password,
			By button_for_list, By nextPage, WebElement user_role)
			throws SQLException, InterruptedException {
		log.info("User counts trips on the page");
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(username, password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		try {
			myTripsPage.gotoRoleTab(user_role);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Expected exception was catched");
		}
		Thread.sleep(3000);
		numPage = myTripsPage.countTrips(button_for_list, nextPage);
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageAfterLogin.logout();
	}

	// passenger joins to the specific trip
	public void joinToTrip(String username, String password,
			String from_address, String to_address, String idtr) {
		log.info("Passenger joins to the specific trip");
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(username, password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		mainPageAfterLogin.joinTripByPassenger(from_address, to_address, idtr);
		mainPageAfterLogin.logout();
	}

	// go to specific tab depends on user"s role
	public void goToUserTab(String username, String password,
			WebElement user_role) {
		log.info(" User goes the goToUserTab method");
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(username, password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		try {
			myTripsPage.gotoRoleTab(user_role);
		} catch (Exception e) {
			log.error("Expected exception was catched");
		}
		log.info("gotoRoleTab was succesful ");
	}

	@SuppressWarnings("rawtypes")
	public void goToPassStatus(String username, String password, String idtr,
			String pass_status) {
		log.info("Passenger set specific status");
		myTripsPage = new MyTripsPage();
		try {
			myTripsPage.setStatusToPassengerTrip(pass_status);
		} catch (Exception e) {
			log.error("Expected exception was catched");
		}
		mainPageAfterLogin = myTripsPage.gotoMainPage();
		mainPageAfterLogin.logout();
	}

	public void getStatusFromPassPage(String username, String password,
			String verification) {
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(username, password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		try {
			myTripsPage.gotoRoleTab(asPassengerTab);
		} catch (Exception e) {
			log.error("Expected exception was catched");
		}
		log.info("gotoRoleTab was succesful");
		myTripsPage.statusIsSubmitted(verification);
	}

	public void getStatusFromDriverPage(String username, String password,
			String verification) {
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(username, password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		myTripsPage = mainPageAfterLogin.goToMyTripsPage();
		try {
			myTripsPage.gotoRoleTab(asDriverTab);
		} catch (Exception e) {
			log.error("Expected exception was catched");
		}
		log.info("gotoRoleTab was succesful ");
		myTripsPage.gotoMyDetailsAsDriver();
		myTripsPage.statusIsSubmittedOnDriverPage(verification);
	}
	
	public static String getMethodName(final int depth)
	{
	  final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

	  //System. out.println(ste[ste.length-depth].getClassName()+"#"+ste[ste.length-depth].getMethodName());
	  // return ste[ste.length - depth].getMethodName();  //Wrong, fails for depth = 0
	  return ste[ste.length - 1 - depth].getMethodName(); //Thank you Tom Tresansky
	}

}
