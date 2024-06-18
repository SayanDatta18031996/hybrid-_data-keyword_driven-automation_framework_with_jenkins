package com.testbot.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.testbot.base.TestBase;

public class AddCustomerTest extends TestBase {

	@Test(dataProvider = "getData")
	public void addCustomer(String FirstName, String LastName, String postCode, String alertText) throws InterruptedException {
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
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		log.info("Alert present");
		
		// Verify that the alert text contains the expected text
		Assert.assertTrue(alert.getText().contains(alertText));
		
		// Accept the alert
		alert.accept();
		
		// Manually fail the test to indicate that the alert was not found
		//Assert.fail("Alert not found");
	}

	@DataProvider
	public Object[][] getData() {
		// Define the sheet name to read data from
		String sheetName = "addCustomer";
		
		// Get the row and column count from the Excel sheet
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		
		// Initialize a 2D array to hold the data
		Object[][] data = new Object[rows - 1][cols];
		
		// Loop through the rows and columns to retrieve data from the Excel sheet
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < cols; colNum++) {
				// Store the data in the 2D array
				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		
		// Return the data for use in the test
		return data;
	}
}
