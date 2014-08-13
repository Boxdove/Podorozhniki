package com.epam.podorozhniki.us.US_01_4_2;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MyTripsPage;

public class US01_4_2_6Test {

	@Test(groups = { "FirstSuit" })
	public void checkPresenceOfFilterFreeSeatsTest() {
		MainPageAfterLogin mainPage = new MainPageAfterLogin();
		MyTripsPage myRoutsPage = mainPage.goToMyTripsPage();
		myRoutsPage.gotoDriverCalendar();
		
		Assert.assertTrue(
				myRoutsPage.isFilterSeatsFreePresent(),
				"Filter \"Free Seats\" is not on the page!");
		Reporter.log("Done", 2, true);
	}
}
