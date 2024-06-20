package com.testbot.testcases;

import org.testng.annotations.Test;
import com.testbot.base.TestBase;
import com.testbot.utilities.TestUtil;

public class OpenAccountTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider="dataProvider")
	public void addCustomer(String Customer, String Currency) throws InterruptedException {
		
		

	}

}
