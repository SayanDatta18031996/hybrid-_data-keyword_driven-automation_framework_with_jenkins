package com.testbot.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import com.testbot.base.TestBase;
import com.testbot.utilities.TestUtil;

public class AddCustomerTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider="dataProvider")
	public void addCustomer(String FirstName, String LastName, String postCode, String alertText, String runMode) throws InterruptedException {
		if(!runMode.equals("Y")) {
			throw new SkipException("Skkiping the execution as runmode is set as No");
		}
		
		// Log the action of getting the locator for the addCustomer tab
		log.info("Getting locator for addCustomer tab");
		
		// Find and click the addCustomer tab
		//WebElement addCustomerNav = driver.findElement(By.xpath(OR.getProperty("addCustomerNav")));
		//addCustomerNav.click();
		clicking("addCustomerNav_xpath");
		
		// Log the action of getting the locator for the first name input field
		log.info("Getting locator for first name of customer");
		
		// Find and enter the first name
		//WebElement firstName = driver.findElement(By.xpath(OR.getProperty("addCustomerFirstName")));
		//firstName.sendKeys(FirstName);
		typing("addCustomerFirstName_xpath",FirstName);
		// Find and enter the last name
		//WebElement lastName = driver.findElement(By.xpath(OR.getProperty("addCustomerLastName")));
		//lastName.sendKeys(LastName);
		typing("addCustomerLastName_xpath",LastName);
		// Find and enter the postal code
		//WebElement postCodeElement = driver.findElement(By.xpath(OR.getProperty("addCustomerPostCode")));
		//postCodeElement.sendKeys(postCode);
		typing("addCustomerPostCode_css",postCode);
		// Find and click the add customer button
		//WebElement addCustomerButton = driver.findElement(By.xpath(OR.getProperty("addCustomerButton")));
		//addCustomerButton.click();
		clicking("addCustomerButton_xpath");
		// Wait for the alert to be present
		Thread.sleep(3000);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		log.info("Alert present");
		
		// Verify that the alert text contains the expected text
		Assert.assertTrue(alert.getText().contains(alertText));
		
		// Accept the alert
		alert.accept();
		
		// Manually fail the test to indicate that the alert was not found
		//Assert.fail("Alert not found");
		Thread.sleep(3000);
	}
}
