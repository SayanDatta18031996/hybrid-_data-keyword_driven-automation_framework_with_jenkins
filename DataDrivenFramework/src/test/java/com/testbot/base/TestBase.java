package com.testbot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.core.config.Configurator;
//import org.apache.logging.log4j.core.config.properties.PropertiesConfiguration;

public class TestBase {
	/*
	 * WebDriver - done Properties - done Logs - log4j jar, .log, log4j.properties,
	 * Logger ExtentReports DB Excel Mail ReportNG, ExtentReports, Jenkins
	 * 
	 */
	public static WebDriver driver;// will be initializing in runtime to handle the object.So, whatever browser
									// information will pass from config file.
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	protected Logger log = null;

	@BeforeSuite
	public void setUp() {
		this.log = LogManager.getLogger(this.getClass());
		if (driver == null) {
			FileInputStream fisConfig = null, fisOR = null;
			try {
				fisConfig = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fisConfig);
				log.debug("Config file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fisOR = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				OR.load(fisOR);
				log.debug("OR file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//String log4jConfigFile = "Log4j2.properties";
            //InputStream is = getClass().getClassLoader().getResourceAsStream(("user.dir") + "\\src\\test\\resources\\properties\\" + log4jConfigFile);
            //if (is != null) {
               // Configurator.initialize(null, "log4j2.properties");
               // log.debug("Log4j2 configuration loaded !!!");
           // } else {
            //    log.error("Log4j2 configuration file not found");
           // }

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

			Integer implicitWaitTimeInSeconds = Integer.parseInt(config.getProperty("implicit.wait")); //
			driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTimeInSeconds));

		}
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}
}
