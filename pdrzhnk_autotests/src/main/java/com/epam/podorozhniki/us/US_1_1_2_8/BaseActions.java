package com.epam.podorozhniki.us.US_1_1_2_8;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.db.DBConnection;
import com.epam.podorozhniki.ui.MethodsPage;

/*
 * Created by Zoja_Sharova
 */

public class BaseActions extends MethodsPage {

	public ReadingDatafile rd;
	private DBConnection dbConnect;

	private static Logger log = Logger.getLogger(BaseActions.class);

	ResultSet rs_driver;
	ResultSet rs_pass;

	@Before
	public void setUp() {
		Driver.init();
		rd = new ReadingDatafile();
		rd.readingDataFile();
		Driver.getInstance().manage().window().maximize();
		Driver.getInstance().get(rd.baseUrl);
	}

	@After
	public void afterTest() throws SQLException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		dbConnect = new DBConnection();
		rs_pass = dbConnect.queryExecutor(rd.queryDeletingAllPassengerTrips);
		rs_driver = dbConnect.queryExecutor(rd.queryDeletingAllDriverTrips);
		log.info("Trips were deleted");
		Driver.tearDown();
	}
}
