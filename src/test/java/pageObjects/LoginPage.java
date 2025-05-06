package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	// locators
	@FindBy(how = How.ID, using = "input-email")
	WebElement txtemail;
	@FindBy(how = How.ID, using = "input-password")
	WebElement txtpassword;
	@FindBy(how = How.XPATH, using = "//input[@type='submit' and @value='Login']")
	WebElement btnlogin;

	// Methods

	public void setUsername(String username) {
		txtemail.sendKeys(username);
	}

	public void setPassword(String password) {
		txtpassword.sendKeys(password);
	}

	public void login() {
		btnlogin.click();
	}

}
