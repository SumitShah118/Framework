package com.facebook.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.facebook.qa.base.TestBase;
import com.facebook.qa.pages.HomePage;
import com.facebook.qa.pages.LoginPage;
import com.facebook.qa.util.TestUtil;

public class LoginPageTest extends TestBase{
	
	LoginPage loginpage;
	HomePage homepage;
	String sheetName = "Login";
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		loginpage = new LoginPage();
	}
	
	@Test(priority=1)
	public void verifyLoginPageTitleTest() {
		String actualTitle = loginpage.verifyLoginPageTitle();
		String expectedTitle = "Facebook – log in or sign up";
		Assert.assertEquals(actualTitle, expectedTitle, "Login Page title not matched");
	}
	
	@Test(priority=2)
	public void verifyLogoTest() {
		boolean flag = loginpage.verifyFacebookLogo();
		Assert.assertTrue(flag);
	}

	/*THE BELOW IS THE TEST METHOD WHICH USED TO CALL DATA FROM CONFIG.PROPERTIES FILE 
	@Test(priority=3)
	public void loginFacebookPageTest() {
		homepage = loginpage.userLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	 */
	
	//BELOW METHOD GET DATA FROM EXTERNAL EXCEL SHEET WHICH IS PLACED IN TESTDATA FOLDER
	@DataProvider
	public Object[][] getFacebookTestData() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=3, dataProvider="getFacebookTestData")
	public void loginFacebookPageTest(String email, String pwd) {
		loginpage.userLogin(email, pwd);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
