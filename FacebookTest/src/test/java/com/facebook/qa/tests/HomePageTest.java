package com.facebook.qa.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.facebook.qa.base.TestBase;
import com.facebook.qa.pages.HomePage;
import com.facebook.qa.pages.LoginPage;
import com.facebook.qa.pages.ProfilePage;
import com.facebook.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	
	LoginPage loginpage;
	HomePage homepage;
	ProfilePage profilepage;
	ExtentTest logger;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeTest
	public void startUp() {
		TestUtil.extentReportInitialization();
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
		logger = extent.createTest("FACEBOOK HOMEPAGE TITLE VERIFICATION TEST");
		logger.log(Status.INFO,"Facebook HomePage Title Verification");
		String title = homepage.verifyHomePageTitle();
		Assert.assertEquals(title, "Facebook", "HomePage Title not matched");
	}
	
	@Test(priority=2)
	public void verifyHomePageLabelTest() {
		logger = extent.createTest("FACEBOOK HOMEPAGE LABLE VERIFICATION TEST");
		logger.log(Status.INFO,"Facebook Homepage label verification");
		boolean flag = homepage.verifyHomePageLabel();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void clickOnProfileLinkTest() {
		profilepage = homepage.clickOnProfileLink();
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE) {
			logger.fail(result.getThrowable().getMessage());
			logger.fail("Testcase Failed "+result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.getScreenShot()).build());
			//logger.addScreenCaptureFromPath(TestUtil.getScreenShot());
		}else if(result.getStatus()==ITestResult.SUCCESS) {
			logger.pass(result.getTestName());
			logger.pass("Testcase Passed "+result.getTestName(),MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.getScreenShot()).build());
		}else {
			logger.skip("Testcase Skipped "+result.getTestName());
		}
		driver.close();
		extent.flush();
	}
}
