package com.facebook.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.facebook.qa.util.TestUtil;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	//public static String filePath=System.getProperty("user.dir")+"/target/myExtentReport.html";
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	@BeforeSuite
	public void extentReportSetup() {
		String filePath = System.getProperty("user.dir")+ "/target/myExtentReport"+TestUtil.getCurrentDateTime()+".html";
	    reporter = new ExtentHtmlReporter(filePath);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}
	
	public TestBase(){
		try {
		prop = new Properties();
		FileInputStream read_file = new FileInputStream("S:\\JavaforSelenium\\FacebookTest\\src\\main\\java\\com\\"
				+ "facebook\\qa\\config\\config.properties");
		prop.load(read_file);
		}catch(FileNotFoundException ie) {
			ie.printStackTrace();
		}catch(IOException ie) {
			ie.printStackTrace();
		}
	}
	
	public static void initialization() {
		
		//THIS IS PURELY FOR BLOCKING UNNECESSARY POPUPS
		/*
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
			*/
		String browser=prop.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","c:\\Program Files\\Java\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("FF")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Java\\geckodriver.exe");
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", "C:\\Program Files\\Java\\internetexplorer.exe");
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGELOAD_TIMEOUT,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_TIMEOUT,TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}
}
