package com.testbot.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.testbot.base.TestBase;

public class TestUtil extends TestBase {

    // Variables to hold screenshot name and path
    public static String screenshotName;
    public static String screenshotPath;

    // Method to capture a screenshot
    public static void CaptureScreenshot() throws IOException {
        // Capture the screenshot as a file from the WebDriver instance
        File ScreenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Create a timestamp to uniquely name the screenshot file
        Date d = new Date();
        String timestamp = d.toString().replace(":", "_").replace(" ", "_");
      
        // Set the screenshot name using the timestamp
        screenshotName = "error_" + timestamp + ".jpg";

        // Set the screenshot path to the target directory within the project
        screenshotPath = System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName;

        // Copy the screenshot file to the specified path
        FileUtils.copyFile(ScreenshotFile, new File(screenshotPath));
    }
    
    @DataProvider(name="dataProvider")
	public Object[][] getData(Method m) {
		// Define the sheet name to read data from
		String sheetName = m.getName();
		
		// Get the row and column count from the Excel sheet
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		
		// Initialize a 2D array to hold the data
		//Object[][] data = new Object[rows - 1][cols];
		Object[][] data = new Object[rows - 1][1];// to use hashtable making the column number 1
		Hashtable<String, String> table=null;
		// Loop through the rows and columns to retrieve data from the Excel sheet
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			table=new Hashtable<String,String>();
			for (int colNum = 0; colNum < cols; colNum++) {
				// Store the data in the 2D array
				//data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);//used for without Hashtable
				table.put(excel.getCellData(sheetName,colNum, 1),excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] =table;
			}
		}
		
		// Return the data for use in the test
		return data;
	}
}
