package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegisterPage extends BasePage {
	// Constructor
	public RegisterPage(WebDriver driver) {
		super(driver);
	}

	// Locators
	@FindBy(how = How.ID, using = "input-firstname")
	WebElement txt_firstname;
	@FindBy(id = "input-lastname")
	WebElement txt_lastname;
	@FindBy(how = How.ID, using = "input-email")
	WebElement txt_email;
	@FindBy(id = "input-telephone")
	WebElement txt_telephone;
	@FindBy(how = How.ID, using = "input-password")
	WebElement txt_password;
	@FindBy(id = "input-confirm")
	WebElement txt_confirmpassword;
	@FindBy(how = How.XPATH, using = "//label[contains(normalize-space(), 'No')]/input[@type='radio' and @name='newsletter']")
	WebElement rb_Subscribe;
	@FindBy(xpath = "//input[@type='checkbox' and @name='agree']")
	WebElement cb_privacypolicy;
	@FindBy(xpath = "//input[@value='Continue' and @type='submit']")
	WebElement btn_submit;
	@FindBy(how = How.XPATH, using = "//h1[contains(normalize-space(), 'Your Account Has Been Created!')]")
	WebElement lbl_accountcreated;

	// Methods
	public void setFirstname(String firstname) {
		txt_firstname.sendKeys(firstname);
	}

	public void setlastname(String lastname) {
		txt_lastname.sendKeys(lastname);
	}

	public void setEmail(String email) {
		txt_email.sendKeys(email);
	}

	public void setTelephone(String telephone) {
		txt_telephone.sendKeys(telephone);
	}

	public void setPassword(String pwd) {
		txt_password.sendKeys(pwd);
	}

	public void setConfirmPwd(String pwd) {
		txt_confirmpassword.sendKeys(pwd);
	}

	public void setSubscribe() {
		rb_Subscribe.click();
	}

	public void setPrivacy() {
		cb_privacypolicy.click();
	}

	public void clickContinute() {
		btn_submit.click();
	}

	public String getMessage() {
		try {
			return (lbl_accountcreated.getText());
		} catch (Exception e) {
			return (e.toString());
		}
	}
}
