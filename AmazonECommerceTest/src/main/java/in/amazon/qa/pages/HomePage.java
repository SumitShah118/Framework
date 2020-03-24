package in.amazon.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import in.amazon.qa.base.TestBase;

public class HomePage extends TestBase {
	
	@FindBy(id="twotabsearchtextbox")
	WebElement searchBar;
	
	@FindBy(xpath="//*[contains(text(),\"Hello, Sumit\")]")
	WebElement loginName;
	
	@FindBy(xpath="//*[@type=\"submit\"]")
	WebElement submit;
	
	public HomePage() {
		PageFactory.initElements(driver,this);
	}
	
	public String verifyHomePageTitle() {
		String title=driver.getTitle();
		return title;
	}
	
	public boolean verifyLoginNameTitle() {
		boolean flag=loginName.isDisplayed();
		return flag;
	}
	
	public MobileListPage verifySearchBarText(String searchText) {
		searchBar.sendKeys(searchText);
		searchBar.sendKeys(Keys.ARROW_DOWN);
		submit.click();
		return new MobileListPage();
	}

}
