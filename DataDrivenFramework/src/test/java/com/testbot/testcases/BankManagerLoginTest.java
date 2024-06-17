package com.testbot.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.testbot.base.TestBase;

public class BankManagerLoginTest extends TestBase {
	@Test
	public void LoginAsManager() throws InterruptedException {
		//this line uses as a flag which will allow to run html code in testng report.
		System.setProperty("org.uncommons.reporting.escape-output", "false");
		log.info("Inside LoginAsManager");
		driver.findElement(By.xpath(OR.getProperty("bmlbtn"))).click();
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("addCustomerNav"))), "Login not successfull");
		log.info("LoginAsManager executed successfully");
		Reporter.log("LoginAsManager executed successfully");
		// Adding screenshot link in the report
		/*Reporter.log("<a target=\"_blank\" href=\"C:\\Users\\ASUS\\Downloads\\logo.jpg\">Screenshot</a>");
		Reporter.log("<a target=\"_blank\" href=\"C:\\Users\\ASUS\\Downloads\\logo.jpg\"><img src=\"C:\\Users\\ASUS\\Downloads\\logo.jpg\" height= 200 width=200></img></a>");*/
		
		Assert.fail("login not successfull");//manually failed the test
	}
}
