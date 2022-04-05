package com.qa.linkedin.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.log4testng.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	private Logger log=Logger.getLogger(TestBase.class);
	public static WebDriver driver=null; 
	public WebDriverWait wait=null;
	//public Properties prop=null;
	
	public String readPropertyValue(String key) throws IOException {
		/**
		 * Read the property from properties file
		 * create an object for the properties class
		 * read the the file 
		 */
		log.info("create object for Properties class");
		
		Properties prop=new Properties();
		log.debug("read the properties file");
		try{
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\config\\config.properties");
			log.info("load all the properties");
			prop.load(fis);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
		
	}
	@BeforeSuite
	public void setup() throws IOException  {
		log.info("started excuting the @BeforeSuite");
		log.debug("fetching browser value from the config.properties file");
		String browserName=readPropertyValue("browser");
		log.debug("launching the"+browserName+"browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	} else if (browserName.equalsIgnoreCase("firefox")) {
		//create object for chromeoptions
		//FirefoxOptions opt=new FirefoxOptions();
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}
	log.info("maximize the window");
	driver.manage().window().maximize();
	log.info(" adding implicit wait");
	driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
	log.info(" create Object for WebDriverWait class");
	wait = new WebDriverWait(driver, Duration.ofMillis(5000));
	driver.get(readPropertyValue("applicationUrl"));
	log.debug("launching the application:"+readPropertyValue("applicationUrl"));
}

@AfterSuite
public void afterClass() {
	if (driver != null) {
	log.debug("close the browser");
		driver.quit();
	}
		
		
	}
	
	
	

}
