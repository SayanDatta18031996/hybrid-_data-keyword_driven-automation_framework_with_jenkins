package com.testbot.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.testbot.base.TestBase;

public class LoginTest extends TestBase {
	@Test
	public void LoginAsManager() throws InterruptedException {
		log.debug("Inside LoginAsManager");
		driver.findElement(By.xpath(OR.getProperty("bmlbtn"))).click();
		Thread.sleep(1000);

	}
}
