package com.testbot.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.testbot.base.TestBase;
import com.testbot.utilities.TestUtil;

public class OpenAccountTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dataProvider")
	public void openAccount(String Customer, String Currency,String alertText) throws InterruptedException {
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
		Assert.assertTrue(alert.getText().contains(alertText));
		
		// Accept the alert
		alert.accept();

	}

}
