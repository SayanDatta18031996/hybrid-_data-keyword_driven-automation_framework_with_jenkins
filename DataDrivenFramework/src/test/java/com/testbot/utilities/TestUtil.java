package com.testbot.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.testbot.base.TestBase;

public class TestUtil extends TestBase {

    public static String screenshotName;
    public static String screenshotPath;
    
    public static void CaptureScreenshot() throws IOException {
        File ScreenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Date d=new Date();
        String timestamp = d.toString().replace(":", "_").replace(" ", "_");
        //String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss_zz"));// second method to make time stamp name of a file.
        screenshotName = "error_" + timestamp + ".jpg";
        screenshotPath = System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName;
        FileUtils.copyFile(ScreenshotFile, new File(screenshotPath));
    }

}
