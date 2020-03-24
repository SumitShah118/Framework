package in.amazon.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import in.amazon.qa.util.TestUtil;

public class TestBase {

	public static Properties prop;
	public static WebDriver driver;
	
	public TestBase() {
		
		try {
		prop = new Properties();
		FileInputStream fis=new FileInputStream("S:\\SeleniumPractice\\AmazonECommerceTest"
				+ "\\src\\main\\java\\in\\amazon\\qa\\config\\config.properties");
		prop.load(fis);
		}catch(FileNotFoundException e1){
			e1.printStackTrace();
		}catch(IOException  e2) {
			e2.printStackTrace();
		}
	}
	
	public static void initialization() {
		String browser = prop.getProperty("BROWSER");
		if(browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver","c:\\Program Files\\Java\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browser.equals("InternetExplorer")) {
			System.setProperty("webdriver.ie.driver","c:\\Program Files\\Java\\internetexplorerdriver.exe");
			driver = new InternetExplorerDriver();
		}else if(browser.equals("Firefox")){
			System.setProperty("webdriver.firefox.driver","c:\\Program Files\\Java\\firefoxdriver.exe");
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("URL"));
	}
}
