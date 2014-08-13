package com.epam.podorozhniki.us.US_01_4_2;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MyTripsPage;

public class US01_4_2_11_1Test {

	final int NUMBER_OF_COLUME = 4;
	final int QTY_OF_SEATSTOTAL = 1;

	@Test(groups = { "SecondSuit" })
	public void selectionOfParticularTotalSeatsTest() throws SQLException {
		MainPageAfterLogin mainPage = new MainPageAfterLogin();
		MyTripsPage myRoutsPage = mainPage.goToMyTripsPage();
		myRoutsPage.gotoDriverCalendar();
		myRoutsPage.particularSelectionInSeatsTotalFilter();
		
		// this user has one trip with 5 total seats
		int expectedResultOfPaticularSelectionInSeatsTotalFilter = QTY_OF_SEATSTOTAL;		
		int actual = myRoutsPage.countTripsInColume(NUMBER_OF_COLUME);
		
		Assert.assertEquals(actual,
				expectedResultOfPaticularSelectionInSeatsTotalFilter,
				"The results in colume on the page and expected is not same!");
		
		Reporter.log("Done", 2, true);
	}
}