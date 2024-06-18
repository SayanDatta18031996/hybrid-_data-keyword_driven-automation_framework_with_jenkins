package com.testbot.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.testbot.base.TestBase;

public class OpenAccountTest extends TestBase {

	@Test(dataProvider = "getData")
	public void addCustomer(String Customer, String Currency) throws InterruptedException {
		
		log.info("Getting locator for addCustomer tab");
		
		
		clicking("addCustomerNav_xpath");
		
		
		log.info("Getting locator for first name of customer");
		
		
		typing("addCustomerFirstName_xpath",FirstName);
		
		typing("addCustomerLastName_xpath",LastName);

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
