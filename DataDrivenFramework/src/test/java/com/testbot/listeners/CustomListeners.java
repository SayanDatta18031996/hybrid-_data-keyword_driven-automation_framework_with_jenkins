package com.testbot.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.testbot.base.TestBase;
import com.testbot.utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
    	 test = reports.createTest(result.getMethod().getMethodName().toUpperCase());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS,result.getName().toUpperCase()+" Pass");
        reports.flush();
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	 try {
    	        TestUtil.CaptureScreenshot();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	    Reporter.log("<a target=\"_blank\" href=\"" + TestUtil.screenshotPath + "\">Screenshot</a>");
    	    Reporter.log("<br>");
    	    Reporter.log("<a target=\"_blank\" href=\"" + TestUtil.screenshotPath + "\"><img src=\"" + TestUtil.screenshotPath + "\" height=200 width=200></img></a>");
    	    test.log(Status.FAIL, result.getName().toUpperCase() + " Failed with exception: " + result.getThrowable());
    	    test.fail("Screenshot",
			        MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.screenshotPath).build());
    	    reports.flush();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
    }

}
