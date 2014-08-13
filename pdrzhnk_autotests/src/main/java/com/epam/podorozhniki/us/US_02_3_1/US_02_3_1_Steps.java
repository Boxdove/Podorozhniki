package com.epam.podorozhniki.us.US_02_3_1;

import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.ui.AddTripPage;
import com.epam.podorozhniki.ui.MainPageAfterLogin;
import com.epam.podorozhniki.ui.MainPageBeforeLogin;
import com.epam.podorozhniki.ui.MyTripsPage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Yana_Velbovets on 6/27/2014.
 */

public class US_02_3_1_Steps {

    public String idtr = "";

    private AddTripPage addTripPage;
    private MyTripsPage myTripsPage;
    private MainPageBeforeLogin mainPageBeforeLogin;
    private MainPageAfterLogin mainPageAfterLogin;


    @Before
    public void creatingATestTrip() {
        Driver.init();
        Driver.getInstance().manage().window().maximize();
        Driver.getInstance().get("http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main");
        mainPageBeforeLogin = new MainPageBeforeLogin();
        mainPageBeforeLogin.assertFieldsArePresent();
        mainPageBeforeLogin.enterLoginAndPass(System.getProperty("LoginDriver"), System.getProperty("PasswordDriver"));
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
        myTripsPage = mainPageAfterLogin.goToMyTripsPage();
        addTripPage = myTripsPage.gotoAddTripPage();
        try {
            myTripsPage = addTripPage.addTrip(System.getProperty("from"), System.getProperty("to"), System.getProperty("AlertTripSaved"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        idtr = myTripsPage.getTripId();
        mainPageBeforeLogin = myTripsPage.logout();
    }


    @After
    public void deleteTheTrip() {
        mainPageBeforeLogin.assertFieldsArePresent();
        mainPageBeforeLogin.enterLoginAndPass(System.getProperty("LoginDriver"), System.getProperty("PasswordDriver"));
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
        myTripsPage = mainPageAfterLogin.goToMyTripsPage();
        myTripsPage.removePassengerTrip();
        mainPageBeforeLogin = myTripsPage.logout();
        Driver.tearDown();
    }


    @Test
    public void recievingConfirmationAboutRegistrationAcceptance() {
        mainPageBeforeLogin.assertFieldsArePresent();
        mainPageBeforeLogin.enterLoginAndPass(System.getProperty("LoginPassenger"), System.getProperty("PasswordPassenger"));
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
        mainPageAfterLogin.joinTripByPassenger(System.getProperty("from"), System.getProperty("to"), idtr, "2");
        myTripsPage = mainPageAfterLogin.goToMyTripsPage();
        myTripsPage.gotoAsPassengerTab();
        myTripsPage.statusIsSubmitted(System.getProperty("verificationSubmitted"));
        mainPageBeforeLogin = myTripsPage.logout();
        mainPageBeforeLogin.assertFieldsArePresent();
        mainPageBeforeLogin.enterLoginAndPass(System.getProperty("LoginDriver"), System.getProperty("PasswordDriver"));
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
        myTripsPage = mainPageAfterLogin.goToMyTripsPage();
        myTripsPage.acceptPassengerTrip(idtr);
        mainPageBeforeLogin = myTripsPage.logout();
        mainPageBeforeLogin.assertFieldsArePresent();
        mainPageBeforeLogin.enterLoginAndPass(System.getProperty("LoginPassenger"), System.getProperty("PasswordPassenger"));
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
        myTripsPage = mainPageAfterLogin.goToMyTripsPage();
        myTripsPage.gotoAsPassengerTab();
        assertEquals(System.getProperty("verificationAccepted"), myTripsPage.getStatus());
        mainPageBeforeLogin = myTripsPage.logout();
    }


    @Test
    public void amountOfFreeSeatsIsDisplayedCorrectly() {
        mainPageBeforeLogin.assertFieldsArePresent();
        mainPageBeforeLogin.enterLoginAndPass(System.getProperty("LoginPassenger"), System.getProperty("PasswordPassenger"));
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
        mainPageAfterLogin.joinTripByPassenger(System.getProperty("from"), System.getProperty("to"), idtr, "2");
        mainPageBeforeLogin = myTripsPage.logout();
        mainPageBeforeLogin.assertFieldsArePresent();
        mainPageBeforeLogin.enterLoginAndPass(System.getProperty("LoginDriver"), System.getProperty("PasswordDriver"));
        mainPageAfterLogin = mainPageBeforeLogin.pressTheLoginButton();
        myTripsPage = mainPageAfterLogin.goToMyTripsPage();
        myTripsPage.acceptPassengerTrip(idtr);
        myTripsPage.gotoMyTripsMainPage();
        myTripsPage.gotoAsDriverTab();
        assertFalse(myTripsPage.amountOfSeatsLeft(System.getProperty("ammOfSeats")));
        assertNotEquals(System.getProperty("ammOfSeats"), myTripsPage.curentAmountOfSeatsLeft());
        mainPageBeforeLogin = myTripsPage.logout();
    }
}
