package com.epam.podorozhniki.us.US_1_1_2_8;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.BaseActions;
import com.epam.podorozhniki.core.DBConnection;
import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.core.ReadingDatafile;
import com.epam.podorozhniki.core.SeleniumRunner;

/*
 * Created by Zoja_Sharova
 */

@RunWith(SeleniumRunner.class)
public class TC_1128_5 extends BaseActions {

	// deleting trip by passenger

	public TC_1128_5() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	private CommonTests commonTests;
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
	protected int tripStatus;

	@FindBy(xpath = "//li[@id='li_driver']/a")
	protected WebElement asDriverTab;

	@FindBy(xpath = "//li[@id='li_passenger']/a")
	protected WebElement asPassengerTab;

	// checking after deleting trip by passenger on passenger page
	// @Test
	// public void passRemovesTripAcceptedStatusOnPassPage()
	// throws InterruptedException, SQLException {
	// rd = new ReadingDatafile();
	// rd.readingDataFile();
	// commonTests = new CommonTests();
	// commonTests.deletingTripWithPassengerByPass(rd.accepted);
	// us_Methods = new US_Methods();
	// us_Methods.getStatusFromPassPage(rd.passenger_username,
	// rd.passenger_password, "cancelled");
	// }
	//
	// @Test
	// public void passRemovesTripDeniedStatusOnPassPage()
	// throws InterruptedException, SQLException {
	// rd = new ReadingDatafile();
	// rd.readingDataFile();
	// commonTests = new CommonTests();
	// commonTests.deletingTripWithPassengerByPass(rd.denied);
	// us_Methods = new US_Methods();
	// us_Methods.getStatusFromPassPage(rd.passenger_username,
	// rd.passenger_password, "cancelled");
	// }
	//
	// @Test
	// public void passRemovesTripSubmittedStatusOnPassPage()
	// throws InterruptedException, SQLException {
	// rd = new ReadingDatafile();
	// rd.readingDataFile();
	// commonTests = new CommonTests();
	// commonTests.deletingTripWithPassengerByPass(rd.submitted);
	// us_Methods = new US_Methods();
	// us_Methods.getStatusFromPassPage(rd.passenger_username,
	// rd.passenger_password, "cancelled");
	// }
	//
	// // checking after deleting trip by passenger on driver page
//	@Test
//	public void passRemovesTripAcceptedStatusCheckOnDriverPage()
//			throws InterruptedException, SQLException {
//		rd = new ReadingDatafile();
//		rd.readingDataFile();
//		commonTests = new CommonTests();
//		commonTests.deletingTripWithPassengerByPass(rd.accepted);
//		us_Methods = new US_Methods();
//		us_Methods.getStatusFromDriverPage(rd.driver_username,
//				rd.driver_password, "cancelled");
//		System.out.println(" (\\___//)");
//		System.out.println("  (='.'=)");
//		System.out.println(" ('')_('')");
//	}

	//
	@Test
	public void passRemovesTripDeniedStatusCheckOnDriverPage()
			throws InterruptedException, SQLException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		commonTests = new CommonTests();
		commonTests.deletingTripWithPassengerByPass(rd.denied);
		us_Methods = new US_Methods();
		us_Methods.getStatusFromDriverPage(rd.driver_username,
				rd.driver_password, "cancelled");
		
	}
	//
	// @Test
	// public void passRemovesTripSubmittedStatusCheckOnDriverPage()
	// throws InterruptedException, SQLException {
	// rd = new ReadingDatafile();
	// rd.readingDataFile();
	// commonTests = new CommonTests();
	// commonTests.deletingTripWithPassengerByPass(rd.submitted);
	// us_Methods = new US_Methods();
	// us_Methods.getStatusFromDriverPage(rd.driver_username,
	// rd.driver_password, "cancelled");
	// }
	//
	// @Test
	// public void passRemovesTripSubmittedStatusFromBase()
	// throws InterruptedException, SQLException {
	// rd = new ReadingDatafile();
	// rd.readingDataFile();
	// commonTests = new CommonTests();
	// commonTests.deletingTripWithPassengerByPass(rd.submitted);
	// idtr_for_delete = commonTests.idtr_for_delete;
	// new DBConnection();
	// tripStatus =
	// queryGetInt("select count(id_trip) as rowcout from trip where id_trip = "
	// + idtr_for_delete + "");
	// Assert.assertEquals(tripStatus, 2);
	// }
	//
	// @Test
	// public void passRemovesTripDeniedStatusFromBase()
	// throws InterruptedException, SQLException {
	// rd = new ReadingDatafile();
	// rd.readingDataFile();
	// commonTests = new CommonTests();
	// commonTests.deletingTripWithPassengerByPass(rd.denied);
	// idtr_for_delete = commonTests.idtr_for_delete;
	// new DBConnection();
	// tripStatus =
	// queryGetInt("select count(id_trip) as rowcout from trip where id_trip = "
	// + idtr_for_delete + "");
	// Assert.assertEquals(tripStatus, 2);
	// }
	//
	// @Test
	// public void passRemovesTripAcceptedStatusFromBase()
	// throws InterruptedException, SQLException {
	// rd = new ReadingDatafile();
	// rd.readingDataFile();
	// commonTests = new CommonTests();
	// commonTests.deletingTripWithPassengerByPass(rd.accepted);
	// idtr_for_delete = commonTests.idtr_for_delete;
	// new DBConnection();
	// tripStatus =
	// queryGetInt("select count(id_trip) as rowcout from trip where id_trip = "
	// + idtr_for_delete + "");
	// Assert.assertEquals(tripStatus, 2);
	// }
}