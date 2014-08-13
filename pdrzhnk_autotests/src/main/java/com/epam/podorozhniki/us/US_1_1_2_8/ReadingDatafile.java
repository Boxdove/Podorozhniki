package com.epam.podorozhniki.us.US_1_1_2_8;

import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.Driver;

/*
 * Created by Zoja_Sharova
 */

public class ReadingDatafile {
	public ReadingDatafile() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	public String driver_username;
	public String driver_password;
	public String passenger_username;
	public String passenger_password;
	public String from_address;
	public String to_address;
	public String from_address_1;
	public String to_address_1;
	public String from_address_2;
	public String to_address_2;
	public String queryDeletingAllDriverTrips;
	public String query;
	public String queryAsDriver;
	public String queryAsPassenger;
	public String queryDeletingAllPassengerTrips;
	public String alert;
	public String baseUrl;
	public String submitted; 
	public String denied;
	public String accepted;
	public String filePath; 
	

	public void readingDataFile() {
		queryDeletingAllDriverTrips = System
				.getProperty("US1128.queryDeletingAllDriverTrips");
		passenger_username = System.getProperty("US1128.passenger_login");
		passenger_password = System.getProperty("US1128.passenger_password");
		driver_username = System.getProperty("US1128.driver_login");
		driver_password = System.getProperty("US1128.driver_password");
		query = System.getProperty("US001.query");
		queryDeletingAllDriverTrips = System
				.getProperty("US1128.queryDeletingAllDriverTrips");
		queryAsDriver = System.getProperty("US1128.queryAsDriver");
		queryAsPassenger = System.getProperty("US1128.queryAsPassenger");
		queryDeletingAllPassengerTrips = System
				.getProperty("US1128.queryDeletingAllPassengerTrips");
		from_address = System.getProperty("US1128.from_adress");
		to_address = System.getProperty("US1128.to_adress");
		from_address_1 = System.getProperty("US1128.from_adress_1");
		to_address_1 = System.getProperty("US1128.to_adress_1");
		from_address_2 = System.getProperty("US1128.from_adress_2");
		to_address_2 = System.getProperty("US1128.to_adress_2");
		alert = System.getProperty("US1128.alert");
		baseUrl = System.getProperty("baseUrl");
		accepted = System.getProperty("US1128.accepted");
		denied = System.getProperty("US1128.denied");
		submitted =System.getProperty("US1128.submitted");
		filePath = System.getProperty("US1128.filePath"); 
	}
	
}
