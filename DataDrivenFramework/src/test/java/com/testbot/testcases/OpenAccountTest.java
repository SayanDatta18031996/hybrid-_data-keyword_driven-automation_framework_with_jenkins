package com.testbot.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.testbot.base.TestBase;
import com.testbot.utilities.TestUtil;

public class OpenAccountTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dataProvider")
	//public void openAccount(String Customer, String Currency,String alertText) throws InterruptedException// without hashtable implementation. 
	public void openAccount(Hashtable<String, String> data) throws InterruptedException {
        // Check if the test should be skipped
        //if (!data.get("runMode").equals("Y")) {
         //   throw new SkipException("Skipping the execution as runMode is set to No");
        //}
		clicking("addOpenAccountNav_css");
		clicking("customerNameSelection_xpath");
		clicking("currencySelection_xpath");
		clicking("currencySelection_xpath");
		clicking("proccessButton_xpath");
		Thread.sleep(3000);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		log.info("Alert present");
		// String alertText="Account created successfully";
		// Verify that the alert text contains the expected text
		Assert.assertTrue(alert.getText().contains(data.get("alertText")), "Alert text validation failed!");
		
		// Accept the alert
		alert.accept();
	}
}
