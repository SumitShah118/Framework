package in.amazon.qa.pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import in.amazon.qa.base.TestBase;

public class MobileListPage extends TestBase {

	@FindBy(xpath="//*[@class=\"a-size-medium a-color-base a-text-normal\" and text()=\"Redmi Note 8 (Neptune Blue, 4GB RAM, 64GB Storage)\"]")
	WebElement clickOnMobileList;
	
	public MobileListPage() {
		PageFactory.initElements(driver,this);
	}
	
	public String verifyMobileListPageTitle() {
		String title=driver.getTitle();
		return title;
	}
	
	public MobileDetailsPage clickOnMobilePhone() {
		String parentWindow = driver.getWindowHandle();
		clickOnMobileList.click();
		Set<String> set = driver.getWindowHandles();
		Iterator<String> itr= set.iterator();
		while(itr.hasNext()) {
			String childWindow=itr.next();
			if(!parentWindow.equals(childWindow)) {
				driver.switchTo().window(childWindow);
			}
		}
		return new MobileDetailsPage();
	}
}

