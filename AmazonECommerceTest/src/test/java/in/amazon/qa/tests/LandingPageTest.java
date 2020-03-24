package in.amazon.qa.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import in.amazon.qa.base.TestBase;
import in.amazon.qa.pages.LandingPage;
import in.amazon.qa.pages.LoginPage;
import junit.framework.Assert;

public class LandingPageTest extends TestBase {

	LandingPage landingpage;
	LoginPage loginpage;
	
	public LandingPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		landingpage = new LandingPage();
		loginpage = new LoginPage();
	}
	
	@Test(priority=0)
	public void verifyLandingPageTitleTest() {
		String title=landingpage.verifyLandingPageTitle();
		Assert.assertEquals("Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in", title);
	}
	/*
	@Test(priority=1)
	public void verifyAmazonLogoTest() {
		boolean flag=landingpage.verifyAmazonLogo();
		Assert.assertTrue(flag);		
	}
	
	@Test(priority=2)
	public void clickOnSignInPageTest() {
		loginpage = landingpage.clickOnSignIn();
	}*/
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
