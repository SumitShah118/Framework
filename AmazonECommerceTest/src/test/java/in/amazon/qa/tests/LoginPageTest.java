package in.amazon.qa.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import in.amazon.qa.base.TestBase;
import in.amazon.qa.pages.HomePage;
import in.amazon.qa.pages.LandingPage;
import in.amazon.qa.pages.LoginPage;
import in.amazon.qa.util.TestUtil;

public class LoginPageTest extends TestBase {
	
	LandingPage landingpage;
	LoginPage loginpage;
	HomePage homepage;
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeTest
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
	
	
	@Test(priority=2, dataProvider = "getExcelData")
	public void clickOnLoginPageTest(String username, String password) throws InterruptedException {
		homepage = loginpage.clickOnLoginPage(username, password);
	}
	
	@DataProvider(name="getExcelData")
	public Object[][] getExcelDataFromTestUtil() throws IOException {
		Object[][] testdata = TestUtil.readDataFromExcel("TestData");
		return testdata;
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
