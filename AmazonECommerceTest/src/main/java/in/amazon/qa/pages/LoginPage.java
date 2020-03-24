package in.amazon.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import in.amazon.qa.base.TestBase;

public class LoginPage extends TestBase {

	@FindBy(name="email")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(id="continue")
	WebElement continueOnEmail;
	
	@FindBy(id="signInSubmit")
	WebElement submitButton;
	
	@FindBy(xpath="//h1[text()=\"Authentication required\"]")
	WebElement authentication;
	
	@FindBy(xpath="//input[@value=\"sms\"]")
	WebElement smsRadio;
	
	@FindBy(xpath="//input[@id=\"continue\"]")
	WebElement finalCountinue;
	
	public LoginPage() {
		PageFactory.initElements(driver,this);
	}
	
	public String verifyLoginPageTitle() {
		String title=driver.getTitle();
		return title;
	}
	
	public HomePage clickOnLoginPage(String uname, String pwd) {
		username.sendKeys(uname);
		continueOnEmail.click();
		password.sendKeys(pwd);
		submitButton.click();
		if(!smsRadio.isDisplayed()) {
			finalCountinue.click();
		}else if(authentication.isDisplayed()) {
			smsRadio.click();
			finalCountinue.click();
		}
		return new HomePage();
	}
}
