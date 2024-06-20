package com.testbot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.testbot.utilities.ExcelReader;
import com.testbot.utilities.ExtentManager;
import com.testbot.utilities.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	// WebDriver instance to control the browser
	public static WebDriver driver;

	// Properties objects to store configuration and Object Repository
	public static Properties config = new Properties();
	public static Properties OR = new Properties();

	// Logger instance for logging
	public static Logger log = null;

	// ExcelReader instance to read test data from Excel files
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");

	// WebDriverWait instance for explicit waits
	public static WebDriverWait wait;

	// ExtentReports instance for generating test reports
	public ExtentReports reports = ExtentManager.getInstance();

	// ExtentTest instance for logging individual test steps
	public static ExtentTest test;

	@BeforeSuite
	public void setUp() {
		// Initialize the logger
		this.log = LogManager.getLogger(this.getClass());

		// Check if the driver is not already initialized
		if (driver == null) {
			FileInputStream fisConfig = null, fisOR = null, fislog = null;
			try {
				// Load configuration file
				fisConfig = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");

				// Load Object Repository file
				fisOR = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");

				// Load Log4j2 properties file
				fislog = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\log4j2.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			try {
				// Load properties from configuration and OR files
				config.load(fisConfig);
				log.debug("Config file loaded !!!");
				OR.load(fisOR);
				log.debug("OR file loaded !!!");

				// Configure Log4j2
				Configurator.initialize(null,
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\log4j2.properties");
				log.debug("Log4j2 configuration loaded !!!");
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Initialize the browser based on the value from the configuration file
			if (config.getProperty("browser").equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.debug("Firefox launched");
			} else if (config.getProperty("browser").equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				log.debug("Chrome launched");
			} else if (config.getProperty("browser").equals("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				log.debug("Edge launched");
			}

			// Set implicit and explicit wait times
			Integer implicitWaitTimeInSeconds = Integer.parseInt(config.getProperty("implicit.wait"));
			Integer explicitWaitTimeInSeconds = Integer.parseInt(config.getProperty("explicit.wait"));

			// Navigate to the test site URL from the configuration file
			driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTimeInSeconds));
			wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTimeInSeconds));
		}
	}

	// Utility method to check if an element is present on the page
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			log.debug("element found");
			return true;
		} catch (NoSuchElementException e) {
			log.debug("element not found");
			return false;
		}
	}

	public void clicking(String locator) {
		if (locator.endsWith("_css")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_xpath")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
			test.log(Status.INFO, "Clicking on: " + locator);
		} else if (locator.endsWith("_class")) {
			driver.findElement(By.className(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_id")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		} else {
			driver.findElement(By.linkText(OR.getProperty(locator))).click();
		}
	}

	public void typing(String locator, String value) {
		if (locator.endsWith("_css")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
			test.log(Status.INFO, "Typing on: " + locator + " Entered Value is: " + value);
		} else if (locator.endsWith("_xpath")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
			test.log(Status.INFO, "Typing on: " + locator + " Entered Value is: " + value);
		} else if (locator.endsWith("_class")) {
			driver.findElement(By.className(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_id")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		} else {
			driver.findElement(By.linkText(OR.getProperty(locator))).sendKeys(value);
		}
	}

	public static void verifyEquals(String expected, String actual) throws IOException {
		try {
			Assert.assertEquals(expected, actual);
		} catch (Throwable t) {
			TestUtil.CaptureScreenshot();
			// ReportNG
			Reporter.log("<br>" + "veri1cation failure" + t.getMessage() + "</br>");
			Reporter.log("<a target=\"_blank\" href=\"" + TestUtil.screenshotPath + "\"><img src=\""
					+ TestUtil.screenshotPath + "\" height=200 width=200></img></a>");
			// Extent Report
			// Log the failure message and exception in the ExtentReports
			test.log(Status.FAIL, "Verification got Failed with exception: " +t.getMessage() );
			// Attach the screenshot to the ExtentReports
			test.fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.screenshotPath).build());

			// Flush the ExtentReports to ensure all information is written to the report
		}

	}

	@AfterSuite
	public void tearDown() {
		// Quit the browser if it is not null
		if (driver != null)
			driver.quit();
	}
}
