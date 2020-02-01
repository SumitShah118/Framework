package com.facebook.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.facebook.qa.base.TestBase;
import com.facebook.qa.pages.HomePage;
import com.facebook.qa.pages.LoginPage;
import com.facebook.qa.pages.ProfilePage;

public class HomePageTest extends TestBase {
	
	LoginPage loginpage;
	HomePage homepage;
	ProfilePage profilepage;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		loginpage = new LoginPage();
		homepage = new HomePage();
		homepage = loginpage.userLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String title = homepage.verifyHomePageTitle();
		Assert.assertEquals(title, "Facebook", "HomePage Title not matched");
	}
	
	@Test(priority=2)
	public void verifyHomePageLabelTest() {
		boolean flag = homepage.verifyHomePageLabel();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void clickOnProfileLinkTest() {
		profilepage = homepage.clickOnProfileLink();
	}
	
	@AfterMethod
	void tearDown() {
		driver.quit();
	}
	

}
