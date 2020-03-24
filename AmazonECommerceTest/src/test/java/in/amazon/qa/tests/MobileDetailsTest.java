package in.amazon.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import in.amazon.qa.base.TestBase;
import in.amazon.qa.pages.HomePage;
import in.amazon.qa.pages.LandingPage;
import in.amazon.qa.pages.LoginPage;
import in.amazon.qa.pages.MobileDetailsPage;
import in.amazon.qa.pages.MobileListPage;
import in.amazon.qa.pages.PaymentPage;
import in.amazon.qa.util.TestUtil;

public class MobileDetailsTest extends TestBase {
	
	LandingPage landingPage;
	LoginPage loginPage;
	HomePage homePage;
	MobileListPage mobilelistPage;
	MobileDetailsPage mobiledetailspage;
	PaymentPage paymentpage;
	
	public MobileDetailsTest() {
		super();
	}
	
	@BeforeTest
	public void setup() {
		initialization();
		landingPage = new LandingPage();
		loginPage = new LoginPage();
		homePage = new HomePage();
		mobilelistPage = new MobileListPage();
		mobiledetailspage = new MobileDetailsPage();
		paymentpage = new PaymentPage();
		landingPage.clickOnSignIn();
		loginPage.clickOnLoginPage(prop.getProperty("USERNAME"),prop.getProperty("PASSWORD") );
		homePage.verifySearchBarText(TestUtil.SEARCH_BAR_TEXT);
		mobilelistPage.clickOnMobilePhone();
	}
	
	@Test(priority=1)
	public void verifyMobileDetailsTest() {
		String title = mobiledetailspage.verifyMobileDetailsPageTitle();
		Assert.assertEquals(title, "Redmi Note 8 (Neptune Blue, 6GB RAM, 128 GB Storage) - Extra 1,000 Off on Exchange &amp; 6 Month No Cost EMI: Amazon.in: Electronics");
	}
	
	@Test(priority=2)
	public void takeScreenShotTest() throws InterruptedException {
		mobiledetailspage.firstImageVerification();
		mobiledetailspage.seconfImageVerification();
		mobiledetailspage.mobileVideoVerification();
	}
	
	@Test(priority=3)
	public void verifyAmountOfProduct() {
		String mobilePrice = mobiledetailspage.verifyMobilePrice();
		int mobilePrc = Integer.parseInt(mobilePrice);
		if(mobilePrc<=11000) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=4)
	public void verifyExchangeOfferTest() {
		boolean flag=mobiledetailspage.verifyExchangeOffer();
		Assert.assertTrue(flag);
		paymentpage = mobiledetailspage.buyProductWithExchangeOffer();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}