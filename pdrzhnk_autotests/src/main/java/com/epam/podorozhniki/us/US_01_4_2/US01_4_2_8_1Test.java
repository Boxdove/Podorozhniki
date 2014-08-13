package com.epam.podorozhniki.us.US_01_4_2;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MyTripsPage;

public class US01_4_2_8_1Test {
	
	final int NUMBER_OF_COLUME = 1;

	@Test(groups = { "SecondSuit" })
	public void selectionOfParticularFromTripTest() throws SQLException {
		MainPageAfterLogin mainPage = new MainPageAfterLogin();
		MyTripsPage myRoutsPage = mainPage.goToMyTripsPage();
		myRoutsPage.gotoDriverCalendar();
		myRoutsPage.particularSelectionInFromFilter();
		
		int expectedResultOfAllSelectionsInFromFilter = myRoutsPage.countRecordsInColumnFromDb(System.getProperty("test.queryFrom"));//countRecordsFromDb();
		int actual = myRoutsPage.countTripsInColume(NUMBER_OF_COLUME);
		
		Assert.assertEquals(actual, expectedResultOfAllSelectionsInFromFilter,
				"The results in colume on the page and from DB is not same!");
		Reporter.log("Done", 2, true);
	}
}
