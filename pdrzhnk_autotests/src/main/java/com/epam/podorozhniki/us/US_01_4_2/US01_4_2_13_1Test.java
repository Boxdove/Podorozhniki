package com.epam.podorozhniki.us.US_01_4_2;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MyTripsPage;

public class US01_4_2_13_1Test {

	final int NUMBER_OF_COLUME = 6;
	final int QTY_OF_FREESEATS = 1;

	@Test(groups = { "SecondSuit" })
	public void selectionOfParticularFreeSeatsTest() throws SQLException {
		MainPageAfterLogin mainPage = new MainPageAfterLogin();
		MyTripsPage myRoutsPage = mainPage.goToMyTripsPage();
		myRoutsPage.gotoDriverCalendar();
		myRoutsPage.particularSelectionInSeatsFreeFilter();
		
		// this user has one trip with 5 free seats
		int expectedResultOfPaticularSelectionInFreeSeatsFilter = QTY_OF_FREESEATS;		
		int actual = myRoutsPage.countTripsInColume(NUMBER_OF_COLUME);
		
		Assert.assertEquals(actual,
				expectedResultOfPaticularSelectionInFreeSeatsFilter,
				"The results in colume on the page and expected is not same!");
		
		Reporter.log("Done", 2, true);
	}
}
