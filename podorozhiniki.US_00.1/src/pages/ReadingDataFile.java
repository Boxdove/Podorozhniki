package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class ReadingDataFile extends Page {

		public ReadingDataFile(WebDriver driver) {

			super(driver);
		}
		
		
		public static org.apache.log4j.Logger log = Logger
				.getLogger(MainPageService.class); 

		public  ReadingDataFile readingProperty() {
			log.info("Reading property file");
			 Properties prop = new Properties();
		        try {
		            FileInputStream fis = new FileInputStream("src/datas.properties");
		            prop.load(fis);
		            fis.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		           log.error(e.getMessage());
		        }
		        @SuppressWarnings("unchecked")
		        Enumeration<String> e = (Enumeration<String>) prop.propertyNames();
		        while (e.hasMoreElements()) {
		            String key = e.nextElement();
		            System.setProperty(key, prop.getProperty(key));
		            Reporter.log(key + " - " + prop.getProperty(key), 2, true);
		        }
		        log.info("Datas were read");
				return PageFactory.initElements(wdriver, ReadingDataFile.class);
}}
