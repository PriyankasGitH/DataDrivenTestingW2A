package com.w2a.testcases;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.w2a.base.TestBase;

public class LoginTest extends TestBase{
	
	
	@Test
	public void loginAsBankManager() throws InterruptedException {
		
		
		logger.debug("Inside Login Test");
		driver.findElement(By.cssSelector(OR.getProperty("bmlButton"))).click();
		Thread.sleep(3000);
		//log.debug("Login succesfully executed");
	}

}
