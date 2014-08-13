package com.epam.podorozhniki.us.US_02_4_2;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.AddTripPage;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;
import com.epam.podorozhniki.ui.MyTripsPage;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by Viktoriia_Ishchuk on 7/29/2014.
 */
public class autoTest_US_2_4_2 {

    static final Logger logger = Logger.getLogger(autoTest_US_2_4_2.class);

    private MainPageBeforeLogin mainPageBeforeLogin;
    private MainPageAfterLogin mainPageAfterLogin;
    private MyTripsPage myTripsPage;
    private AddTripPage addTripPage;

    public String idtr = ""; //Trip ID (from database)- will generate, when trip is created.

    @Before
    public void setUp() throws InterruptedException {
        logger.info("BEFORE TEST:");
        logger.info("OPEN FIREFOX DRIVER");
        Driver.init();
        Driver.getInstance().manage().window().maximize();
        logger.info("OPEN MAIN PAGE: http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main.");
        Driver.getInstance().get("http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main");
        mainPageBeforeLogin = new MainPageBeforeLogin();
        logger.info("DRIVER LOGIN INTO THE SYSTEM.");
        mainPageBeforeLogin.enterLoginAndPass(System.getProperty("US11.driverLoginName"), System.getProperty("US11.driverLoginPassword"));
        logger.info("OPEN MY TRIPS TAB.");
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
        myTripsPage = mainPageAfterLogin.goToMyTripsPage();
        logger.info("OPEN ADD ROUTE PAGE.");
        addTripPage = myTripsPage.gotoAddTripPage();
        logger.info("DRIVER CREATE NEW TRIP FOR PASSENGER.");
        myTripsPage = addTripPage.addTrip(System.getProperty("US11.fromAddress"), System.getProperty("US11.toAddress"), System.getProperty("AlertTripSaved"));
        logger.info("OPEN MY TRIPS PAGE");
        logger.info("GET NEW TRIP ID.");
        idtr = myTripsPage.getTripId();
        logger.info("Trip ID = " + idtr);
        mainPageAfterLogin = myTripsPage.gotoMainPage();
        logger.info("DRIVER LOGOUT FROM THE SYSTEM");
        mainPageBeforeLogin = mainPageAfterLogin.logout();
    }

    @Test
    public void US11_SUBMITTED() {
        logger.info("START AUTOTEST US11_SUBMITTED.");
        logger.info("US02.4.2 - TRAVELLER'S CALENDAR. TC02.4.2.1 - SUBMITTED");
        logger.info("PASSENGER LOGIN INTO THE SYSTEM.");
        mainPageBeforeLogin.enterLoginAndPass(System.getProperty("US11.passengerLoginName"), System.getProperty("US11.passengerLoginPassword"));
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
        logger.info("PASSENGER FIND CURRENT DRIVER'S TRIP AND JOIN IT.");
        mainPageAfterLogin.joinTripByPassenger(System.getProperty("US11.fromAddress"), System.getProperty("US11.toAddress"), idtr, "1");
        logger.info("PASSENGER OPEN 'My Trips' TAB.");
        myTripsPage = mainPageAfterLogin.goToMyTripsPage();
        logger.info("PASSENGER OPEN 'Passenger Calendar' Tab.");
        myTripsPage.gotoPassengerCalendar();
        logger.info("PASSENGER VERIFY STATUS OF TRIP BY 'Day' VIEW.");
        myTripsPage.verifyTripStatusAsPassengerByDayFilter("SUBMITTED");
        logger.info("PASSENGER VERIFY STATUS OF TRIP BY 'Week' VIEW.");
        myTripsPage.verifyTripStatusAsPassengerByWeekFilter("SUBMITTED");
        logger.info("PASSENGER VERIFY STATUS OF TRIP BY 'Month' VIEW.");
        myTripsPage.verifyTripStatusAsPassengerByMonthFilter("SUBMITTED");
        logger.info("PASSENGER VERIFY STATUS OF TRIP BY 'Year' VIEW.");
        myTripsPage.verifyTripStatusAsPassengerByYearFilter("SUBMITTED");
        logger.info("PASSENGER OPEN MAIN PAGE.");
        mainPageAfterLogin = myTripsPage.gotoMainPage();
    }

    @Test
    public void US11_ACCEPTED() {
        logger.info("START AUTOTEST US11_ACCEPTED.");
        logger.info("US02.4.2 - TRAVELLER'S CALENDAR. TC02.4.2.2 - ACCEPTED.");
        logger.info("PASSENGER LOGIN INTO THE SYSTEM.");
        mainPageBeforeLogin.enterLoginAndPass(System.getProperty("US11.passengerLoginName"), System.getProperty("US11.passengerLoginPassword"));
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
        logger.info("PASSENGER FIND CURRENT DRIVER'S TRIP AND JOIN IT.");
        mainPageAfterLogin.joinTripByPassenger(System.getProperty("US11.fromAddress"), System.getProperty("US11.toAddress"), idtr, "1");
        logger.info("PASSENGER LOGOUT FROM THE SYSTEM.");
        mainPageBeforeLogin = mainPageAfterLogin.logout();
        logger.info("DRIVER LOGIN INTO THE SYSTEM.");
        mainPageBeforeLogin.enterLoginAndPass(System.getProperty("US11.driverLoginName"), System.getProperty("US11.driverLoginPassword"));
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
        logger.info("DRIVER OPEN 'My Trips' TAB.");
        myTripsPage = mainPageAfterLogin.goToMyTripsPage();
        logger.info("DRIVER ACCEPT PASSENGER'S TRIP.");
        myTripsPage.acceptPassengerTrip(idtr); //!!!
        logger.info("DRIVER OPEN MAIN PAGE.");
        mainPageAfterLogin = myTripsPage.gotoMainPage();
        logger.info("DRIVER LOGOUT FROM THE SYSTEM.");
        mainPageBeforeLogin = mainPageAfterLogin.logout();
        logger.info("PASSENGER LOGIN INTO THE SYSTEM.");
        mainPageBeforeLogin.enterLoginAndPass(System.getProperty("US11.passengerLoginName"), System.getProperty("US11.passengerLoginPassword"));
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
        logger.info("PASSENGER OPEN 'My Trips' TAB.");
        myTripsPage = mainPageAfterLogin.goToMyTripsPage();
        logger.info("PASSENGER OPEN 'Passenger Calendar' TAB.");
        myTripsPage.gotoPassengerCalendar();
        logger.info("PASSENGER VERIFY STATUS OF TRIP BY 'Day' VIEW.");
        myTripsPage.verifyTripStatusAsPassengerByDayFilter("ACCEPTED");
        logger.info("PASSENGER VERIFY STATUS OF TRIP BY 'Week' VIEW.");
        myTripsPage.verifyTripStatusAsPassengerByWeekFilter("ACCEPTED");
        logger.info("PASSENGER VERIFY STATUS OF TRIP BY 'Month' VIEW.");
        myTripsPage.verifyTripStatusAsPassengerByMonthFilter("ACCEPTED");
        logger.info("PASSENGER VERIFY STATUS OF TRIP BY 'Year' VIEW.");
        myTripsPage.verifyTripStatusAsPassengerByYearFilter("ACCEPTED");
        logger.info("PASSENGER OPEN MAIN PAGE.");
        mainPageAfterLogin = myTripsPage.gotoMainPage();
    }

    @Test
    public void US11_REJECTED() {
        logger.info("START AUTOTEST US11_ACCEPTED.");
        logger.info("US02.4.2 - TRAVELLER'S CALENDAR.. TC02.4.2.3 - REJECTED");
        logger.info("PASSENGER LOGIN INTO THE SYSTEM.");
        mainPageBeforeLogin.enterLoginAndPass(System.getProperty("US11.passengerLoginName"), System.getProperty("US11.passengerLoginPassword"));
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
        logger.info("PASSENGER FIND CURRENT DRIVER'S TRIP AND JOIN IT.");
        mainPageAfterLogin.joinTripByPassenger(System.getProperty("US11.fromAddress"), System.getProperty("US11.toAddress"), idtr, "1");
        logger.info("PASSENGER LOGOUT FROM THE SYSTEM.");
        mainPageBeforeLogin = mainPageAfterLogin.logout();
        logger.info("DRIVER LOGIN INTO THE SYSTEM.");
        mainPageBeforeLogin.enterLoginAndPass(System.getProperty("US11.driverLoginName"), System.getProperty("US11.driverLoginPassword"));
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
        logger.info("DRIVER OPEN 'My Trips' TAB.");
        myTripsPage = mainPageAfterLogin.goToMyTripsPage();
        logger.info("DRIVER ACCEPT PASSENGER'S TRIP.");
        myTripsPage.rejectPassengerTrip(idtr); //!!!
        logger.info("DRIVER OPEN MAIN PAGE.");
        mainPageAfterLogin = myTripsPage.gotoMainPage();
        logger.info("DRIVER LOGOUT FROM THE SYSTEM.");
        mainPageBeforeLogin = mainPageAfterLogin.logout();
        logger.info("PASSENGER LOGIN INTO THE SYSTEM.");
        mainPageBeforeLogin.enterLoginAndPass(System.getProperty("US11.passengerLoginName"), System.getProperty("US11.passengerLoginPassword"));
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
        logger.info("PASSENGER OPEN 'My Trips' TAB.");
        myTripsPage = mainPageAfterLogin.goToMyTripsPage();
        logger.info("PASSENGER OPEN 'Passenger Calendar' TAB.");
        myTripsPage.gotoPassengerCalendar();
        logger.info("PASSENGER VERIFY STATUS OF TRIP BY 'Day' VIEW.");
        myTripsPage.verifyTripStatusAsPassengerByDayFilter("REJECTED");
        logger.info("PASSENGER VERIFY STATUS OF TRIP BY 'Week' VIEW.");
        myTripsPage.verifyTripStatusAsPassengerByWeekFilter("REJECTED");
        logger.info("PASSENGER VERIFY STATUS OF TRIP BY 'Month' VIEW.");
        myTripsPage.verifyTripStatusAsPassengerByMonthFilter("REJECTED");
        logger.info("PASSENGER VERIFY STATUS OF TRIP BY 'Year' VIEW.");
        myTripsPage.verifyTripStatusAsPassengerByYearFilter("REJECTED");
        logger.info("PASSENGER OPEN MAIN PAGE.");
        mainPageAfterLogin = myTripsPage.gotoMainPage();
    }

    @After
    public void tearDown() {
        logger.info("AFTER TEST:");
        logger.info("PASSENGER LOGOUT FROM THE SYSTEM.");
        mainPageBeforeLogin = mainPageAfterLogin.logout();
        logger.info("DRIVER LOGIN INTO THE SYSTEM");
        mainPageBeforeLogin.enterLoginAndPass(System.getProperty("US11.driverLoginName"), System.getProperty("US11.driverLoginPassword"));
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
        logger.info("OPEN MYTRIPS PAGE");
        myTripsPage = mainPageAfterLogin.goToMyTripsPage();
        logger.info("DRIVER REMOVE CURRENT TRIP.");
        myTripsPage.removePassengerTrip();
        mainPageAfterLogin = myTripsPage.gotoMainPage();
        logger.info("DRIVER LOGOUT FROM THE SYSTEM.");
        mainPageBeforeLogin = mainPageAfterLogin.logout();
        logger.info("CLOSE FIREFOX DRIVER.");
        Driver.tearDown();
        logger.info("ALL SCREENSHOTS AND LOG FILE CAN BE FOUND BY PATH: D:\\Viktoriia_Ishchuk\\gitProjects\\podorozhniki_us11\\screenshots\\");
    }

}
