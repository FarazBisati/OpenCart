package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	// locators
	@FindBy(how = How.XPATH, using = "//h2[normalize-space()='My Account']")
	WebElement lblMyAccount;

	@FindBy(how = How.XPATH, using = "//a[normalize-space()='Logout']")
	WebElement lnkLogout;

	// methods
	public boolean checkLabel() {
		try {
			return (lblMyAccount.isDisplayed());
		} catch (Exception e) {
			return false;
		}
	}

	public void logout() {
		lnkLogout.click();
		;
	}

}
