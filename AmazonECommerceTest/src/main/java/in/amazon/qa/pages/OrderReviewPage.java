package in.amazon.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import in.amazon.qa.base.TestBase;


public class OrderReviewPage extends TestBase {

	@FindBy(xpath="//*[@class=\"spc-left\"]//child::div[1]")
	WebElement headerOfPage;
	
	@FindBy(xpath="//*[@class=\"a-row\"]//strong[text()=\"Billing address\"]//following::a[1]")
	WebElement billingAddrLink;
	
	@FindBy(xpath="//div[@id=\"billing-address-popover\"]//following::div[@class=\"add-address-button-text\" and text()=\"Add a billing address\"][5]")
	WebElement addAddr;
	
	@FindBy(xpath="//h4[text()=\"Choose a billing address\"]")
	WebElement billingAddrHeader;
	
	@FindBy(id="enterAddressFullName")
	WebElement enterFullName;
	
	@FindBy(id="enterAddressPhoneNumber")
	WebElement enterPhoneNumber;
	
	@FindBy(id="enterAddressPostalCode")
	WebElement enterPostalCode;
	
	@FindBy(id="enterAddressAddressLine1")
	WebElement enterAddressLine1;
	
	@FindBy(id="enterAddressCity")
	WebElement enterCity;
	
	@FindBy(id="enterAddressStateOrRegion")
	WebElement state;
	
	@FindBy(xpath="//div[@id=\"address-book-entry-0\"]//a")
	WebElement submitAddress;

	@FindBy(xpath="//*[text()=\"Amazon.in homepage\"]")
	WebElement Amazon_homepage;
	
	public OrderReviewPage() {
		PageFactory.initElements(driver,this);
	}
	
	public String  verifyHeaderOfPage() {
		return headerOfPage.getText();
	}
	
	public String clickOnBillingAddressAndGetHeader() {
		billingAddrLink.click();
		driver.switchTo().frame("a-popover-iframe-1");
		return billingAddrHeader.getText();
	}
	
	public HomePage fillAddress(String fullname, String phonenumber, String pincode, String address, String city, String state1) {
		addAddr.click();
		enterFullName.sendKeys(fullname);
		enterPhoneNumber.sendKeys(phonenumber);
		enterPostalCode.sendKeys(pincode);
		enterAddressLine1.sendKeys(address);
		enterCity.sendKeys(city);
		state.sendKeys(state1);
		submitAddress.click();

		Amazon_homepage.click();
		return new HomePage();
	}

}
