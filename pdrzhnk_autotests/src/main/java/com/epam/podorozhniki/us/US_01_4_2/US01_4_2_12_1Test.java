package com.epam.podorozhniki.us.US_01_4_2;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MyTripsPage;

public class US01_4_2_12_1Test {

	final int NUMBER_OF_COLUME = 5;
	final int QTY_OF_SEATSOCCUPIED = 3;

	@Test(groups = { "SecondSuit" })
	public void selectionOfParticularSeatsOccupiedTest() throws SQLException {
		MainPageAfterLogin mainPage = new MainPageAfterLogin();
		MyTripsPage myRoutsPage = mainPage.goToMyTripsPage();
		myRoutsPage.gotoDriverCalendar();
		myRoutsPage.particularSelectionInSeatsOccupiedFilter();
		
		// this user has three trip with 5 total seats
		int expectedResultOfPaticularSelectionInSeatsOccupiedFilter = QTY_OF_SEATSOCCUPIED;		
		int actual = myRoutsPage.countTripsInColume(NUMBER_OF_COLUME);
		
		Assert.assertEquals(actual,
				expectedResultOfPaticularSelectionInSeatsOccupiedFilter,
				"The results in colume on the page and expected is not same!");
		
		Reporter.log("Done", 2, true);
	}
}
