package com.testbot.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

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
        // Alternative method to create a timestamp for the screenshot file name
        // String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss_zz"));

        // Set the screenshot name using the timestamp
        screenshotName = "error_" + timestamp + ".jpg";

        // Set the screenshot path to the target directory within the project
        screenshotPath = System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName;

        // Copy the screenshot file to the specified path
        FileUtils.copyFile(ScreenshotFile, new File(screenshotPath));
    }

}
