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
		log.info("Getting locator for addCustomer tab");
		WebElement  addCustomerNav= driver.findElement(By.xpath(OR.getProperty("addCustomerNav")));
		addCustomerNav.click();
		log.info("Getting locator for first name of customer");
		WebElement firstName = driver.findElement(By.xpath(OR.getProperty("addCustomerFirstName")));
		firstName.sendKeys(FirstName);
		WebElement lastName = driver.findElement(By.xpath(OR.getProperty("addCustomerLastName")));
		lastName.sendKeys(LastName);
		WebElement postCodeElement = driver.findElement(By.xpath(OR.getProperty("addCustomerPostCode")));
		postCodeElement.sendKeys(postCode);
		WebElement addCustomerButton= driver.findElement(By.xpath(OR.getProperty("addCustomerButton")));
		addCustomerButton.click();
		Alert alert= wait.until(ExpectedConditions.alertIsPresent());
		log.info("Alert present");
		Assert.assertTrue(alert.getText().contains(alertText));
		alert.accept();
		Assert.fail("Alert not found");//manually failed the test
	}

	@DataProvider
	public Object[][] getData(){
		String sheetName="addCustomer";
		int rows=excel.getRowCount(sheetName);
		int cols=excel.getColumnCount(sheetName);
		
		Object[][] data= new Object[rows-1][cols];
		for(int rowNum=2;rowNum<=rows;rowNum++) {
			for(int colNum=0;colNum<cols;colNum++) {
				data[rowNum-2][colNum]=excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		return data;
	}
}
