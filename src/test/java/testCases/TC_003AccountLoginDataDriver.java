package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003AccountLoginDataDriver extends BaseClass {

	@Test(dataProvider = "loginData", dataProviderClass = DataProviders.class, groups = "regression")
	public void LoginDataDriver(String username, String password, String result) {

		try {
			log.info("------------------- Login Data Driven Test Case Starts -------------------");

			HomePage hp = new HomePage(driver);
			hp.clickMyaccounts();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setUsername(username);
			lp.setPassword(password);
			lp.login();

			MyAccountPage ma = new MyAccountPage(driver);
			boolean lbl = ma.checkLabel();

			if (result.equalsIgnoreCase("Valid")) {
				if (lbl == true) {
					ma.logout();
					Assert.assertTrue(true);
				} else {
					Assert.fail();
				}
			} else {
				if (lbl == true) {
					ma.logout();
					Assert.fail();
				} else {
					Assert.assertTrue(true);

				}

			}

		} catch (Exception e) {
			log.error("** error Log ** " + e.getMessage());
			Assert.fail();
		}
	}
}
