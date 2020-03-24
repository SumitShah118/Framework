package in.amazon.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import in.amazon.qa.base.TestBase;

public class LandingPage extends TestBase {

	@FindBy(xpath="//*[contains(text(),\"Hello. Sign in\")]")
	WebElement signIn;
	
	@FindBy(xpath="//div[@id=\"nav-flyout-ya-signin\"]//child::span[text()=\"Sign in\"]")
	WebElement mainSignIn;
	
	@FindBy(xpath="//a[@class=\"nav-logo-link\"]//span[1]")
	WebElement amazonLogo;
	
	public LandingPage() {
		PageFactory.initElements(driver,this);
	}
	
	public String verifyLandingPageTitle() {
		String title=driver.getTitle();
		return title;
	}
	
	public boolean verifyAmazonLogo() {
		boolean flag=amazonLogo.isDisplayed();
		return flag;
	}
	
	public LoginPage clickOnSignIn() {
		Actions act=new Actions(driver);
		act.moveToElement(signIn)
		   .moveToElement(mainSignIn)
		   .click()
		   .build()
		   .perform();
		return new LoginPage();
	}
}
