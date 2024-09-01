package com.w2a.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.w2a.utilities.ExcelReader;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



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
	 protected static final Logger logger = LogManager.getLogger(TestBase.class);
	 public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "/src/test/resources/excel/testdata.xlsx");



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
				
				//logger.debug("Or file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 if(config.getProperty("browser").equals("firefox")) {
				 
				 
				 driver = new FirefoxDriver();
			 }else if(config.getProperty("browser").equals("chrome")) {
				 
				 driver = new ChromeDriver();
				
				// logger.debug("Chrome launched");
				 
			 }else if(config.getProperty("browser").equals("safari")) {
				 
				 driver = new SafariDriver();
			 }
			 driver.get(config.getProperty("testsiteurl"));
			 //logger.debug("Navigated to: " + config.getProperty("testsiteurl"));
			 driver.manage().window().maximize();
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(config.getProperty("implicit.wait"))));
		 
			 
		 }
		 
		 
	 }
	 
	 public boolean isElementPresent(By by) {
		 
		 try {
			 driver.findElement(by);
			 return true;
			 
		 }catch(NoSuchElementException e){
			 
			 return false;
			 
		 }
	 }
	 

	@AfterSuite
	 public void tearDown() {
		 
		 if(driver!= null) {
		 driver.quit();
		 }
		// logger.debug("test execution completed");
		
	 }
	
	
	
}
