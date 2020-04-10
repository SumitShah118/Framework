package com.facebook.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.facebook.qa.base.TestBase;

public class LoginPage extends TestBase {

	@FindBy(id="email")
	WebElement username;
	
	@FindBy(id="pass")
	WebElement password;
	
	@FindBy(id="u_0_b")
	WebElement loginBtn;
	
	@FindBy(xpath="//i[contains(@class,\"fb_logo\"])")
	WebElement facebookLogo;
	
	public LoginPage() {
		PageFactory.initElements(driver, this); 
	}
	
	public String verifyLoginPageTitle() {
		String title = driver.getTitle();
		return title;
	}
	
	public boolean verifyFacebookLogo() {
		return facebookLogo.isDisplayed();
	}
	
	public HomePage userLogin(String un, String pwd) {
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.click();
		
		return new HomePage();
	}
}
