package com.facebook.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.facebook.qa.base.TestBase;
import com.facebook.qa.util.TestUtil;

public class ProfilePage extends TestBase {

	@FindBy(xpath="//a[contains(@class,'_2nlw')]")
	WebElement profileName;
	
	@FindBy(xpath="//*[contains(text(),'Edit Profile')]")
	WebElement editProfileBtn;
	
	@FindBy(xpath="//a[@title='Close']")
	WebElement closeProfileBtn;
	
	@FindBy(xpath="//h3[@id='u_18_0']")
	WebElement editProfileTitle;
	
	@FindBy(xpath="//*[@class='_2md']")
	WebElement facebookLogoLink;
	
	public ProfilePage() {
		PageFactory.initElements(driver,this);
	}
	
	public String verifyProfilePageTitle() throws InterruptedException {
		Thread.sleep(TestUtil.THREAD_SLEEP);
		return driver.getTitle();
	}
	
	public String verifyProfilePageName() {
		return profileName.getText();
	}
	
	public HomePage clickOnFacebookLogo() {
		facebookLogoLink.click();
		return new HomePage();
	}
	
	
	
}




