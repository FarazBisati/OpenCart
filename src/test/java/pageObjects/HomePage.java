package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage {
	// Parameterized constructor
	public HomePage(WebDriver driver) {
		super(driver);
	}

	// locators
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;
	@FindBy(how = How.XPATH, using = "//a[normalize-space()='Register']")
	WebElement lnkRegister;
	@FindBy(how = How.XPATH, using = "//a[normalize-space()='Login']")
	WebElement lnkLogin;

	// Action Methods
	public void clickMyaccounts() {
		lnkMyaccount.click();
	}

	public void clickRegister() {
		lnkRegister.click();
	}

	public void clickLogin() {
		lnkLogin.click();
	}
}
