package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

public class TC_001AccountRegistrationTest extends BaseClass {

	@Test(groups = "Sanity")
	public void testAccountRegistrartion() {
		try {
			log.info("**** TC_001AccountRegistrationTest Starts ****");
			HomePage hp = new HomePage(driver);
			log.info("** Click on My Accounts **");
			hp.clickMyaccounts();
			log.info("** Click on Register **");
			hp.clickRegister();

			log.info("** Enter Registrarion fields **");

			RegisterPage regp = new RegisterPage(driver);
			regp.setFirstname(getRandomString().toUpperCase());
			regp.setlastname(getRandomString().toUpperCase());
			regp.setEmail(getRandomString() + "@gmail.com");
			regp.setTelephone(getRandonNumber());
			String pwd = getRandomPwd().trim();
			regp.setPassword(pwd);
			regp.setConfirmPwd(pwd);
			regp.setSubscribe();
			regp.setPrivacy();
			regp.clickContinute();

			String message = regp.getMessage().trim();

			Assert.assertEquals(message, "Your Account Has Been Created!");
			log.info("** Test Case Passed **");
		} catch (Exception e) {
			log.error("** Error log **" + e.getMessage());
			log.debug("** Debug log **" + e.getMessage());
			log.info("** TestCase failed **");
			Assert.fail();
		} finally {
			log.info("**** TC_001AccountRegistrationTest Ends ****");
		}
	}

}
