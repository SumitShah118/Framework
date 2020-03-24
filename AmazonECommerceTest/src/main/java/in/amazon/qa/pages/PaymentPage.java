package in.amazon.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import in.amazon.qa.base.TestBase;

public class PaymentPage extends TestBase{
	
	@FindBy(id="pp-lHA9bT-98")
	WebElement availableCashCheckbox;
	
	@FindBy(xpath="//*[@id=\"pp-lHA9bT-100\"]")
	WebElement creditCardRadio;
	
	@FindBy(xpath="//div[@id=\"pp-lHA9bT-99\"]//child::div[@class=\"a-column a-span3 pmts-account-holder-name\"]")
	WebElement cardName;
	
	@FindBy(xpath="//*[@id=\"pp-lHA9bT-103\"]")
	WebElement cardeExpiryDate;
	
	@FindBy(id="pp-lHA9bT-131")
	WebElement cardCVV;
	
	@FindBy(xpath="//*[@id=\"pp-lHA9bT-381\"]//child::input[@type=\"submit\" and @name=\"ppw-widgetEvent:SetPaymentPlanSelectContinueEvent\"]")
	WebElement submit;
	
	@FindBy(xpath="//h1[@class=\"a-spacing-base\"]")
	WebElement headingOfPage;
	
	public PaymentPage() {
		PageFactory.initElements(driver,this);
	}
	
	public String verifyPaymentPageTitle() {
		String title=driver.getTitle();
		return title;
	}
	
	public String verifyPaymentHeader() {
		return headingOfPage.getText();
	}
	
	public String verifyCardNameAndCardExpiryDate() {
		return cardName.getText()+" "+cardeExpiryDate.getText();
	}
	
	public OrderReviewPage enterDetailsAndClickOnSubmitButton() {
		availableCashCheckbox.click();
		creditCardRadio.click();
		cardCVV.sendKeys("111");
		submit.click();
		return new OrderReviewPage();
	}
}

