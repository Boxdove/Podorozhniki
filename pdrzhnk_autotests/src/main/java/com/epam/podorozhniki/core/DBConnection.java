package com.epam.podorozhniki.core;

import java.sql.*;

public class DBConnection {
	
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://evbyminsd7238.minsk.epam.com:5432/pdrzh_for_testers";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "123";

	// method that executes query
	public  ResultSet queryExecutor(String query) throws SQLException {
		Connection dbConnection = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			dbConnection = getDBConnection();
			// create SQL stetement
			st = dbConnection.createStatement();
			// execute SQL stetement
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

		if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return rs;
	}

	// method that connect to DB
	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
					DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}
}

/*u can use this class like so:
*	DBConnection db = new DBConnection();
*		String query = "select * from table";
*		ResultSet expectedResult = db.queryExecutor(query);
*		expectedResult.next();
		
*		expectedResult.getInt(1); use method getInt(1)/getString(1) to get int/String result from first colume!!!
*/