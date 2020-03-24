package in.amazon.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import in.amazon.qa.base.TestBase;
import in.amazon.qa.pages.HomePage;
import in.amazon.qa.pages.LandingPage;
import in.amazon.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
	
	LandingPage landingpage;
	LoginPage loginpage;
	HomePage homepage;
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		landingpage = new LandingPage();
		loginpage = new LoginPage();
		homepage = new HomePage();
		landingpage.clickOnSignIn();
	}
	
	@Test(priority=1)
	public void verifyLoginPageTitleTest() {
		String title = loginpage.verifyLoginPageTitle();
		Assert.assertEquals(title,"Amazon Sign In", "Login Page title is not Matching");
	}
	
	@Test(priority=2)
	public void clickOnLoginPageTest() throws InterruptedException {
		homepage = loginpage.clickOnLoginPage(prop.getProperty("USERNAME"), prop.getProperty("PASSWORD"));
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
