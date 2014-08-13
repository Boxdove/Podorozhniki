package com.epam.podorozhniki.us.US_01_4_2;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MyTripsPage;

/**
 * @author Viktoriia_Havryliuk
 *
 */
public class US01_4_2_10_1Test {
	
	final int NUMBER_OF_COLUME = 3;
	final int QTY_OF_CONFIRMEDTRIPSTATUS = 1;

	@Test(groups = { "SecondSuit" })
	public void selectionOfParticularTripStatusTest() throws SQLException {
		MainPageAfterLogin mainPage = new MainPageAfterLogin();
		MyTripsPage myRoutsPage = mainPage.goToMyTripsPage();
		myRoutsPage.gotoDriverCalendar();
		myRoutsPage.particularSelectionInTripStatusFilter();
		
		// this user has one trip with Confirmed status
		int expectedResultOfPaticularSelectionInTripStatusFilter = QTY_OF_CONFIRMEDTRIPSTATUS;		
		int actual = myRoutsPage.countTripsInColume(NUMBER_OF_COLUME);
		
		Assert.assertEquals(actual,
				expectedResultOfPaticularSelectionInTripStatusFilter,
				"The results in colume on the page and expected is not same!");
		
		Reporter.log("Done", 2, true);
	}
}
