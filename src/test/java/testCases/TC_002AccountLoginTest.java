package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002AccountLoginTest extends BaseClass {

	@Test(groups = { "Sanity", "Regression" })
	public void Login() {

		try {
			log.info("---------------- Account Login Test Case starts --------------------------------");
			HomePage hp = new HomePage(driver);
			hp.clickMyaccounts();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setUsername(po.getProperty("username"));
			lp.setPassword(po.getProperty("password"));
			lp.login();

			MyAccountPage my = new MyAccountPage(driver);
			boolean result = my.checkLabel();

			Assert.assertEquals(result, true);
		} catch (Exception e) {
			log.error(" ** Error Log ** " + e.getMessage());
			Assert.fail();
		} finally {
			log.info("------------  Account Login Test Case ends -----------------------");
		}

	}

}
