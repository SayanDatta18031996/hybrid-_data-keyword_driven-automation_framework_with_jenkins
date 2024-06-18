package com.testbot.utilities;

import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    
    // Static instance of ExtentReports to be used across the project
    private static ExtentReports extent;

    // Method to get the ExtentReports instance
    public static ExtentReports getInstance() {
        // Check if the ExtentReports instance is null, meaning it hasn't been initialized yet
        if (extent == null) {
            // Define the path where the report will be generated
            String reportPath = System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\extentReport.html";
            
            // Create an ExtentSparkReporter instance with the report path
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            
            try {
                // Load the ExtentReports configuration from the XML file
                sparkReporter.loadXMLConfig(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\extentconfig\\ReportsConfig.xml"));
            } catch (IOException e) {
                // Handle any exceptions that occur during the loading of the XML configuration
                e.printStackTrace();
            }
            
            // Initialize the ExtentReports instance and attach the SparkReporter to it
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        
        // Return the initialized ExtentReports instance
        return extent;
    }
}
