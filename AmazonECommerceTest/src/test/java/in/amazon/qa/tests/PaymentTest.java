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

public class PaymentTest extends TestBase{
	
	LandingPage landingpage;
	LoginPage loginpage;
	HomePage homepage;
	MobileListPage mobilelistpae;
	MobileDetailsPage mobiledetailspage;
	PaymentPage paymentpage;
	OrderReviewPage orderreviewpage;
	
	public PaymentTest() {
		super();
	}
	
	@BeforeTest
	public void setup() {
		initialization();
		landingpage = new LandingPage();
		loginpage = new LoginPage();
		homepage = new HomePage();
		mobilelistpae = new MobileListPage();
		mobiledetailspage = new MobileDetailsPage();
		paymentpage = new PaymentPage();
		orderreviewpage = new OrderReviewPage();
		landingpage.clickOnSignIn();
		loginpage.clickOnLoginPage(prop.getProperty("USERNAME"),prop.getProperty("PASSWORD"));
		homepage.verifySearchBarText(TestUtil.SEARCH_BAR_TEXT);
		mobilelistpae.clickOnMobilePhone();
		mobiledetailspage.buyProductWithExchangeOffer();
	}
	
	@Test(priority=1)
	public void verifyPaymentPageTitleTest() {
		String title = paymentpage.verifyPaymentPageTitle();
		Assert.assertEquals(title,"Select a Payment Method - Amazon.in Checkout");
	}
	
	@Test(priority=2)
	public void verifyPaymentPageHeaderTest() {
		String header = paymentpage.verifyPaymentHeader();
		Assert.assertEquals(header, "Select a Payment Method");
	}
	
	@Test(priority=3)
	public void verifyCardDetailsTest() {
		String cardDetails = paymentpage.verifyCardNameAndCardExpiryDate();
		Assert.assertEquals(cardDetails, "Sumit Shah 05/2023");
	}
	
	@Test(priority=4)
	public void enterDetailsAndClickOnSubmitTest() {
		orderreviewpage = paymentpage.enterDetailsAndClickOnSubmitButton();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
