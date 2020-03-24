package in.amazon.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import in.amazon.qa.base.TestBase;
import in.amazon.qa.util.TestUtil;

public class MobileDetailsPage extends TestBase{

	@FindBy(xpath="//*[@id=\"a-autoid-13\"]//following::span[@id=\"a-autoid-13-announce\"][2]//img[contains(@src,\"https://images-na.ssl-images-amazon.com/images/I/41mGLH7EUNL._SX38_SY50_CR,0,0,38,50_.jpg\")]")
	WebElement firstImage;
	
	@FindBy(xpath="//img[@src=\"https://images-na.ssl-images-amazon.com/images/I/41mGLH7EUNL._SX38_SY50_CR,0,0,38,50_.jpg\"]")
	WebElement mobVideo;
	
	@FindBy(xpath="//*[@class=\"a-icon a-icon-close\"]")
	WebElement closeVid;	
	
	@FindBy(xpath="//img[@src=\"https://images-na.ssl-images-amazon.com/images/I/41ZIcdMdrnL._SX38_SY50_CR,0,0,38,50_.jpg\"]")
	WebElement secondImage;
	
	@FindBy(xpath="//tr[@id=\"priceblock_ourprice_row\"]//td[2]")
	WebElement mobPrice;
	
	@FindBy(xpath="//*[text()=\"With Exchange\"]")
	WebElement withExchange;
	
	@FindBy(xpath="//i[@class=\"a-icon a-accordion-radio a-icon-radio-inactive\"]")
	WebElement radioButton; 
	
	@FindBy(xpath="//*[@class=\"a-dropdown-prompt\" and text()=\"Select Brand\"]")
	WebElement brand;
	
	@FindBy(xpath="//*[@id=\"buyBackDropDown1_21\"]")
	WebElement selBrand;
	
	@FindBy(xpath="//*[@id=\"buyBackDropDowns2\"]//span[34]/span[@id=\"XiaomiId\"]//following::span[text()=\"Select Model\"]")
	WebElement model;
	
	@FindBy(xpath="//a[@id=\"Motorola_16\"]")
	WebElement selModel;
	
	@FindBy(id="buyBackIMEITextInput")
	WebElement enterIMEINumber;
	
	@FindBy(name="//input[@name=\"buyBackApplyButton\"]")
	WebElement enter;
	
	@FindBy(xpath="//span[@id=\"buyBackBuyNowButton\"]//input[@name=\"submit.buy-now\"]")
	WebElement buyNowWithExchage;
	
	public MobileDetailsPage() {
		PageFactory.initElements(driver,this);
	}
	
	public String verifyMobileDetailsPageTitle() {
		String title=driver.getTitle();
		return title;
	}
	
	public void firstImageVerification() throws InterruptedException {
		firstImage.click();
		TestUtil.takeScreenshot(driver);
		Thread.sleep(TestUtil.THREAD_SLEEP_TIMEOUT);
	}
	
	public void seconfImageVerification() throws InterruptedException {
		secondImage.click();
		TestUtil.takeScreenshot(driver);
		Thread.sleep(TestUtil.THREAD_SLEEP_TIMEOUT);
	}
	
	public void mobileVideoVerification() throws InterruptedException {
		mobVideo.click();
		TestUtil.takeScreenshot(driver);
		for(int i=0;i<4;i++) {
			Thread.sleep(TestUtil.THREAD_SLEEP_TIMEOUT);
		}
		closeVid.click();
	}
	
	public String verifyMobilePrice() {
		String amount=mobPrice.getText();
		return amount;
	}
	
	public boolean verifyExchangeOffer() {
		boolean flag=withExchange.isDisplayed();
		radioButton.click();
		brand.click();
		selBrand.click();
		model.click();
		selModel.click();
		enterIMEINumber.sendKeys(TestUtil.IMEI_NUMBER);
		enter.click();
		return flag;
	}
	
	public PaymentPage buyProductWithExchangeOffer() {
		buyNowWithExchage.click();
		return new PaymentPage();
	}
}
