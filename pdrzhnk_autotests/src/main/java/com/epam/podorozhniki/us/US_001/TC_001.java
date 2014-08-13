package com.epam.podorozhniki.us.US_001;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.core.ReadingDatafile;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;

/*
 * Created by Zoja_Sharova
 */

public class TC_001 {

	public int numFromPage;
	public int numFromBase;

	private MainPageAfterLogin mainPageAfterLogin;
	private MainPageBeforeLogin mainPageBeforeLogin;
	private ReadingDatafile rd; 
	
	private static Logger log = Logger.getLogger(TC_001.class);

	private By buttonJoin = By.xpath("//button[contains(text(),'Join')]");
	private By nextPage = By
			.xpath("//li[@class='active']/following-sibling::*[1]/self::li/a");

	@Before
	public void setUp() throws InterruptedException {
		Driver.init();
		rd = new ReadingDatafile(); 
		rd.readingDataFile();
		Driver.getInstance().manage().window().maximize();
		Driver.getInstance().get(rd.baseUrl);
	}

	@Test
	public void countRowsOnThePage() throws SQLException {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		mainPageBeforeLogin = new MainPageBeforeLogin();
		mainPageBeforeLogin.enterLoginAndPass(rd.driver_username, rd.driver_password);
		mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
		numFromPage = mainPageAfterLogin.countTrips(buttonJoin, nextPage);
		System.out.println(numFromPage);
		mainPageAfterLogin.logout();
		mainPageAfterLogin.queryGetInt(rd.query);
		numFromBase = mainPageAfterLogin.queryGetInt(rd.query);
		log.info(numFromBase);
		log.info(numFromPage);
		assertEquals("Number of trips is not correct", numFromBase, numFromPage);
	}

	@After
	public void tearDown() throws Exception {
		Driver.tearDown();
	}
}
