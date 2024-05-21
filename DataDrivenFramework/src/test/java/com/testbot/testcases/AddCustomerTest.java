package com.testbot.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.testbot.base.TestBase;
import com.testbot.utilities.ExcelReader;

import java.io.IOException;

public class AddCustomerTest extends TestBase {

    @Test(dataProvider = "getData")
    public void addCustomer(String FirstName, String LastName, String postCode) {
    	log.info("Getting locator for first name of customer");
    	WebElement firstName = driver.findElement(By.xpath(OR.getProperty("addCustomerNav")));
        //log.info("Getting locator for first name of customer");
        //WebElement firstName = driver.findElement(By.xpath(OR.getProperty("addCustomerFirstName")));
        //firstName.sendKeys(FirstName);
       // WebElement lastName = driver.findElement(By.xpath(OR.getProperty("addCustomerLastName")));
        //lastName.sendKeys(LastName);
        //WebElement postCodeElement = driver.findElement(By.xpath(OR.getProperty("addCustomerPostCode")));
        //postCodeElement.sendKeys(postCode);
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        ExcelReader excel = new ExcelReader();
        return excel.getData();
    }
}
