package com.w2a.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;



public class TestBase {
	
	
	/*
	 * WebDriver - done
	 * Properties - done
	 * Logs - Log4j jar, .log, log4j.properties, Logger class
	 * ExtentReports
	 * DB	
	 * Excel
	 * Mail
	 * ReportNG, ExtentReports
	 * Jenkins
	 * */
	
	 public static WebDriver driver;
	 public static Properties config = new Properties();
	 public static Properties OR = new Properties();
	 public static FileInputStream fis;
	 //public static Logger log = Logger.getLogger("devpinoyLogger");
	 protected static final Logger logger = LogManager.getLogger(TestBase.class);


	 @BeforeMethod
	@BeforeSuite
	 public void setUp() {
		 
		 if(driver==null) {
			 
			try {
				logger.info("Starting Selenium test");
				fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 try {
				config.load(fis);
				//log.debug("Config file loaded");
				//log.debug("Config file loaded");
				logger.debug("Config file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 try {
				fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties/OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 try {
				OR.load(fis);
				//log.fine("Or file loaded");
				//.debug("Or file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 if(config.getProperty("browser").equals("firefox")) {
				 
				 
				 driver = new FirefoxDriver();
			 }else if(config.getProperty("browser").equals("chrome")) {
				 
				 driver = new ChromeDriver();
				 //log.fine("Chrome launched");
				// log.debug("Chrome launched");
				 
			 }else if(config.getProperty("browser").equals("safari")) {
				 
				 driver = new SafariDriver();
			 }
			 driver.get(config.getProperty("testsiteurl"));
			// log.fine("Navigated to :" + config.getProperty("testsiteurl"));
			 //log.debug("Navigated to: " + config.getProperty("testsiteurl"));
			 driver.manage().window().maximize();
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(config.getProperty("implicit.wait"))));
		 
			 
		 }
		 
		 
	 }
	 
	@AfterMethod
	@AfterSuite
	 public void tearDown() {
		 
		 if(driver!= null) {
		 driver.quit();
		 }
		// log.debug("test execution completed");
		
	 }
	
	
	
}
