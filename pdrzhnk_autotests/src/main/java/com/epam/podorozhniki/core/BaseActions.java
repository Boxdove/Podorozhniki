package com.epam.podorozhniki.core;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.MethodsPage;

public class BaseActions extends MethodsPage {

	public static ReadingDatafile rd;
	private static DBConnection dbConnect;

	private static Logger log = Logger.getLogger(BaseActions.class);

	static ResultSet rs_driver;
	static ResultSet rs_pass;

	@Rule
	public TestRule screenshotRule = new TestWatcher() {

		@Override
		protected void starting(Description description) {
			Driver.init();
			System.out
					.println("*********************************************************************************************************");
			rd = new ReadingDatafile();
			rd.readingDataFile();
			Driver.getInstance().manage().window().maximize();
			Driver.getInstance().get(rd.baseUrl);
		}

		@Override
		protected void finished(Description description) {
			rd = new ReadingDatafile();
			rd.readingDataFile();
			dbConnect = new DBConnection();
			try {
				rs_pass = dbConnect
						.queryExecutor(rd.queryDeletingAllPassengerTrips);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs_driver = dbConnect
						.queryExecutor(rd.queryDeletingAllDriverTrips);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info("AfterClass: Trips were deleted");
			System.out
					.println("*********************************************************************************************************");
			Driver.tearDown();
		}

		@Override
		protected void failed(Throwable e, Description description) {
			captureScreenshot(description.getMethodName());
		}
	};

	private void captureScreenshot(String name) {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		try {
			String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss")
					.format(Calendar.getInstance().getTime());
			String fileID = name + "_" + timeStamp;
			String screenName = String.format("%s.png", fileID);
			String path = "D://EPAM/screenshots/" + screenName;
			File screenshot = ((TakesScreenshot) Driver.getInstance())
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(path));
		} catch (IOException e) {

		}
	}

}
