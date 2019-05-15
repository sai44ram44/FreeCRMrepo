package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.crm.qa.utils.TestUtil;
import com.crm.qa.utils.WebEventListener;

public class TestBase {

	
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebDriverEventListener eventListener;
	
	public TestBase() {
		
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream("C:\\Users\\padampullaiah\\eclipse-workspace\\FreeCRMTest"
					+ "\\src\\main\\java\\com\\crm\\qa\\config\\test.properties");
			
			prop.load(fis);
		}
		catch(FileNotFoundException e) {
			
			e.printStackTrace();
		}
		catch(IOException e2) {
			e2.printStackTrace();
		}
	}
	
	public static void initialization() {
		
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", "E:\\Sairam\\Selenium Drivers\\chromedriver.exe");
			
			//1. headless chrome browser tesing
			//ChromeOptions options = new ChromeOptions();
			//options.setHeadless(true);
			//driver = new ChromeDriver(options);
			
			
			//2. headless using htmlunit driver
			// add external jar htmlunit-driver-2.33.3-jar-with-dependencies
			//driver = new HtmlUnitDriver();
			
			driver = new ChromeDriver();
					
		}
		else if(browserName.equals("firefox")) {
			
			System.setProperty("webdriver.gecko.driver", "E:\\Sairam\\Selenium Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			
		}
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().deleteAllCookies();
		//driver.manage().deleteCookieNamed("Cookies and other site data");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	
	}
	
}
