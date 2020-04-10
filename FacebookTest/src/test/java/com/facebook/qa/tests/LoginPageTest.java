package com.facebook.qa.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.facebook.qa.base.TestBase;
import com.facebook.qa.pages.HomePage;
import com.facebook.qa.pages.LoginPage;
import com.facebook.qa.util.TestUtil;

public class LoginPageTest extends TestBase{
	
	LoginPage loginpage;
	HomePage homepage;
	String sheetName = "Login";
	//ExtentTest logger1;
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeTest
	public void startUp() {
		TestUtil.extentReportInitialization();
	}
	
	@BeforeMethod
	public void sedtup() {
		initialization();
		loginpage  = new LoginPage();	
	}
	
	@Test(priority=1)
	public void verifyLoginPageTitleTest() {
		logger = extent.createTest("FACEBOOK LOGIN PAGE TITLE TEST");
		logger.log(Status.INFO,"Facebook login title test");
		String actualTitle = loginpage.verifyLoginPageTitle();
		String expectedTitle = "Facebook – log in or sign up";
		Assert.assertEquals(actualTitle, expectedTitle, "Login Page title not matched");
	}
	
	@Test(priority=2)
	public void verifyLogoTest() {
		logger = extent.createTest("FACEBOOK VERIFY LOGO TEST");
		logger.log(Status.INFO,"Facebook logo Test");
		boolean flag = loginpage.verifyFacebookLogo();
		Assert.assertTrue(flag);
	}

	//BELOW METHOD GET DATA FROM EXTERNAL EXCEL SHEET WHICH IS PLACED IN TESTDATA FOLDER
	@DataProvider
	public Object[][] getFacebookTestData() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=3, dataProvider="getFacebookTestData")
	public void loginFacebookPageTest(String email, String pwd) {
		logger = extent.createTest("FACEBOOK INPUT CREDENTIALS TEST");
		logger.log(Status.INFO,"Facebook input credential test");
		loginpage.userLogin(email, pwd);
		Assert.assertTrue(true);
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE) {
			logger.fail(result.getThrowable().getMessage());
			logger.fail(result.getThrowable().getMessage()+" testcase Failed",MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.getScreenShot()).build());
			//logger.addScreenCaptureFromPath(TestUtil.getScreenShot());
		}else if(result.getStatus()==ITestResult.SUCCESS) {
			logger.pass(result.getTestName());
			logger.pass(result.getTestName()+" testcase Passed", MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.getScreenShot()).build());
		}else {
			logger.skip(result.getTestName()+" testcase skipped");
		}
		extent.flush();
		driver.close();
	}
}
