package com.facebook.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.facebook.qa.base.TestBase;

public class HomePage extends TestBase {
	

	@FindBy(xpath="//*[@class='_1vp5'][contains(text(),\"Sumit\")]")
	WebElement label;
	
	@FindBy(xpath="//*[contains(text(),\"Sumit Shah\")][contains(@class,'linkWrap')]")
	WebElement profileLink;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public boolean verifyHomePageLabel() {
		return label.isDisplayed();
	}
	
	public ProfilePage clickOnProfileLink() {
		profileLink.click();
		return new ProfilePage();
	}
}
