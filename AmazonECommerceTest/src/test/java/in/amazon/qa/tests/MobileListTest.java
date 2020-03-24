package in.amazon.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import in.amazon.qa.base.TestBase;
import in.amazon.qa.pages.HomePage;
import in.amazon.qa.pages.LandingPage;
import in.amazon.qa.pages.LoginPage;
import in.amazon.qa.pages.MobileDetailsPage;
import in.amazon.qa.pages.MobileListPage;
import in.amazon.qa.util.TestUtil;

public class MobileListTest extends TestBase {
	
	LandingPage landingpage;
	LoginPage loginpage;
	HomePage homepage;
	MobileListPage mobilelistpage;
	MobileDetailsPage mobiledetailspage;
	
	public MobileListTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException {
		initialization();
		landingpage = new LandingPage();
		loginpage = new LoginPage();
		homepage = new HomePage();
		mobilelistpage = new MobileListPage();
		mobiledetailspage = new MobileDetailsPage();
		landingpage.clickOnSignIn();
		loginpage.clickOnLoginPage(prop.getProperty("USERNAME"),prop.getProperty("PASSWORD"));
		homepage.verifySearchBarText(TestUtil.SEARCH_BAR_TEXT);
	}
	
	@Test(priority=1)
	public void verifyMobileListPageTitleTest() {
		String title = mobilelistpage.verifyMobileListPageTitle();
		Assert.assertEquals(title, "Amazon.in : Redmi note 8 6gb 128gb");
	}
	
	@Test(priority=2)
	public void clickOnMobilePhoneTest() {
		mobiledetailspage = mobilelistpage.clickOnMobilePhone();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
