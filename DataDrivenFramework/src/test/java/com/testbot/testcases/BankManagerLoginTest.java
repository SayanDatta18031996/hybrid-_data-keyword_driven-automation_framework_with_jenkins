package com.testbot.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.testbot.base.TestBase;

public class BankManagerLoginTest extends TestBase {
	@Test
	public void LoginAsManager() throws InterruptedException, IOException {
		// Set system property to allow HTML code in TestNG reports
		System.setProperty("org.uncommons.reporting.escape-output", "false");
		
		verifyEquals("abc", "xyz");
		// Log the start of the login test
		log.info("Inside LoginAsManager");
		
		// Find and click the Bank Manager Login button
		//driver.findElement(By.xpath(OR.getProperty("bmlbtn"))).click();
		clicking("bmlbtn_xpath");
		
		// Assert that the Add Customer navigation element is present after login
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("addCustomerNav_xpath"))), "Login not successful");
		
		// Log the successful execution of the login test
		log.info("LoginAsManager executed successfully");
		
		// Report the successful execution of the login test
		Reporter.log("LoginAsManager executed successfully");
		
		// Uncomment the following lines to add a screenshot link in the report
		/*
		Reporter.log("<a target=\"_blank\" href=\"C:\\Users\\ASUS\\Downloads\\logo.jpg\">Screenshot</a>");
		Reporter.log("<a target=\"_blank\" href=\"C:\\Users\\ASUS\\Downloads\\logo.jpg\"><img src=\"C:\\Users\\ASUS\\Downloads\\logo.jpg\" height= 200 width=200></img></a>");
		*/
		
		// Manually fail the test to indicate that the login was not successful
		Assert.fail("Login not successful");
	}
}
