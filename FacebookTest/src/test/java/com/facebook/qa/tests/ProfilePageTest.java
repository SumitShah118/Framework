package com.facebook.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.facebook.qa.base.TestBase;
import com.facebook.qa.pages.HomePage;
import com.facebook.qa.pages.LoginPage;
import com.facebook.qa.pages.ProfilePage;
import com.facebook.qa.util.TestUtil;

public class ProfilePageTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	ProfilePage profilepage;
	
	public ProfilePageTest() {
		super();
		
	}

	@BeforeMethod
	public void setup() throws InterruptedException {
		initialization();
		loginpage = new LoginPage();
		homepage = new HomePage();
		profilepage = new ProfilePage();
		homepage = loginpage.userLogin(prop.getProperty("username"), prop.getProperty("password"));
		profilepage = homepage.clickOnProfileLink();
		Thread.sleep(TestUtil.THREAD_SLEEP);
	}
	
	@Test(priority=1)
	public void verifyProfilePageTitleTest() throws InterruptedException {
		String title = profilepage.verifyProfilePageTitle();
		Assert.assertEquals(title, "Sumit Shah", "Title of Profile Page not matched");
	}
	
	@Test(priority=2)
	public void verifyProfilePageNameTest() {
		String name = profilepage.verifyProfilePageName();
		Assert.assertEquals(name,"Sumit Shah", "Profile Page Name is not matched");
	}
	
	@Test(priority=3)
	public void clickOnFacebookPageLogoTest() {
		profilepage.clickOnFacebookLogo();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}

