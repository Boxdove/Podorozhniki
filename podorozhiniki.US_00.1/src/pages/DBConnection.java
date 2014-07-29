package pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DBConnection extends Page {

	public DBConnection(WebDriver driver) {
		super(driver);
	}

	public static String DBUser;
	public static String DBPassword;
	public static String driver;
	public static String DBUrl;
	public int numFromBase;

	public static org.apache.log4j.Logger log = Logger
			.getLogger(MainPageService.class);

	public static ReadingDataFile readingProperty;

	private static Connection getDBConnection() {
		log.info("DBConection");
		readingProperty = PageFactory.initElements(wdriver,
				ReadingDataFile.class);
		DBUser = System.getProperty("DBUser.id");
		DBPassword = System.getProperty("DBPassword.id");
		driver = System.getProperty("driver.id");
		DBUrl = System.getProperty("DBUrl.id");
		Connection dbConnection = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			log.error("Driver for SQL connect was not found");
		}
		try {
			dbConnection = DriverManager.getConnection(DBUrl, DBUser,
					DBPassword);
			log.info(dbConnection);
			return dbConnection;
		} catch (SQLException e) {
			log.error("Error in the connection to the database");
		}
		return dbConnection;
	}

	public ResultSet queryExecutor(String query) throws SQLException {
		log.info("query is performing");
		Connection dbConnection = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			dbConnection = getDBConnection();
			st = dbConnection.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			log.info("SQL statement is not executed");
		} finally {

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return rs;
	}

}
