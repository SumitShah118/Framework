package in.amazon.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import in.amazon.qa.base.TestBase;
import in.amazon.qa.pages.HomePage;
import in.amazon.qa.pages.LandingPage;
import in.amazon.qa.pages.LoginPage;
import in.amazon.qa.pages.MobileListPage;
import in.amazon.qa.util.TestUtil;

public class HomePageTest extends TestBase {

	LandingPage landingpage;
	LoginPage loginpage;
	HomePage homepage;
	MobileListPage mobilelistpage;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeTest
	public void setup() throws InterruptedException {
		initialization();
		landingpage = new LandingPage();
		loginpage = new LoginPage();
		homepage = new HomePage();
		mobilelistpage = new MobileListPage();
		landingpage.clickOnSignIn();
		loginpage.clickOnLoginPage(prop.getProperty("USERNAME"),prop.getProperty("PASSWORD"));
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String title=homepage.verifyHomePageTitle();
		Assert.assertEquals(title, "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
	}
	
	@Test(priority=2)
	public void verifyHomePageNameTest() {
		boolean flag=homepage.verifyLoginNameTitle();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void doSearchingTest() {
		mobilelistpage = homepage.verifySearchBarText(TestUtil.SEARCH_BAR_TEXT);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	
}
