package com.facebook.qa.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
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
		logger = extent.createTest("PROFILE PAGE TITLE TEST");
		logger.log(Status.INFO,"Facebook Profile Title Test");
		String title = profilepage.verifyProfilePageTitle();
		Assert.assertEquals(title, "Sumit Shah", "Title of Profile Page not matched");
	}
	
	@Test(priority=2)
	public void verifyProfilePageNameTest() {
		logger = extent.createTest("PROFILE NAME TEST FOR FACEBOOK");
		logger.log(Status.INFO,"Profile Name Test");
		String name = profilepage.verifyProfilePageName();
		Assert.assertEquals(name,"Sumit Shah", "Profile Page Name is not matched");
	}
	
	@Test(priority=3)
	public void clickOnFacebookPageLogoTest() {
		homepage = profilepage.clickOnFacebookLogo();
		Assert.assertEquals(homepage.verifyHomePageTitle(), "Facebook", "Current page is not HomePage");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE) {
			logger.fail(result.getThrowable().getMessage());
			logger.fail("Testcase Failed "+result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.getScreenShot()).build());
		}else if(result.getStatus()==ITestResult.SUCCESS) {
			logger.pass(result.getTestName());
			logger.pass("Testcase Passed "+result.getTestName(), MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.getScreenShot()).build());
		}else {
			logger.skip("Testcase skipped "+result.getTestName());
		}
		extent.flush();
		driver.close();
	}
}

