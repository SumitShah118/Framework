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
import in.amazon.qa.pages.OrderReviewPage;
import in.amazon.qa.pages.PaymentPage;
import in.amazon.qa.util.TestUtil;

public class OrderReviewTest extends TestBase{
	
	LandingPage landingpage;
	LoginPage loginpage;
	HomePage homepage;
	MobileListPage mobilelistpage;
	MobileDetailsPage mobiledetailspage;
	PaymentPage paymentpage;
	OrderReviewPage orderreviewpage;
	
	public OrderReviewTest() {
		super();
	}
	
	@BeforeTest
	public void setup() {
		initialization();
		landingpage = new LandingPage();
		loginpage = new LoginPage();
		homepage = new HomePage();
		mobilelistpage = new MobileListPage();
		mobiledetailspage = new MobileDetailsPage();
		paymentpage = new PaymentPage();
		orderreviewpage = new OrderReviewPage();
		landingpage.clickOnSignIn();
		loginpage.clickOnLoginPage(prop.getProperty("USERNAME"),prop.getProperty("PASSWORD"));
		homepage.verifySearchBarText(TestUtil.SEARCH_BAR_TEXT);
		mobilelistpage.clickOnMobilePhone();
		mobiledetailspage.buyProductWithExchangeOffer();
		paymentpage.enterDetailsAndClickOnSubmitButton();
	}

	@Test(priority=1)
	public void verifyHeaderTest() {
		String header = orderreviewpage.verifyHeaderOfPage();
		Assert.assertEquals(header, "Review your order");
	}
	
	@Test(priority=2)
	public void verifyBillingHeaderTest() {
		String billingHeader = orderreviewpage.clickOnBillingAddressAndGetHeader();
		Assert.assertEquals(billingHeader, "Choose a billing address");
	}
	
	@Test(priority=3)
	public void fillAddressTest() throws InterruptedException {
		homepage = orderreviewpage.fillAddress(TestUtil.BILLING_FULL_NAME, TestUtil.MOBILE_NUMBER, TestUtil.POSTAL_CODE, TestUtil.ADDRESS_LINE_1, TestUtil.CITY, TestUtil.STATE);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
