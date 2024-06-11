package com.testbot.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.testbot.base.TestBase;

public class BankManagerLoginTest extends TestBase {
	@Test
	public void LoginAsManager() throws InterruptedException {
		log.info("Inside LoginAsManager");
		driver.findElement(By.xpath(OR.getProperty("bmlbtn"))).click();
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("addCustomerNav"))), "Login not successfull");
		log.info("LoginAsManager executed successfully");	
	}
}
