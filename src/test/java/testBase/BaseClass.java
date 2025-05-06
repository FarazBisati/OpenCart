package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger log;
	public Properties po;

	@SuppressWarnings("deprecation")
	@BeforeClass(groups = { "Sanity", "Regression" })
	@Parameters({ "Os", "Browser" })
	public void setup(String os, String br) throws IOException {

		log = LogManager.getLogger(this.getClass());

		FileReader file = new FileReader(".\\src\\test\\resources\\config.properties");
		po = new Properties();
		po.load(file);

		if (po.getProperty("execution_env").equals("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			switch (os.toLowerCase()) {
			case "windows":
				capabilities.setPlatform(Platform.WIN10);
				break;
			case "mac":
				capabilities.setPlatform(Platform.MAC);
				break;
			case "linux":
				capabilities.setPlatform(Platform.LINUX);
				break;
			default:
				System.out.println("Inavlid O/S");
				return;
			}

			switch (br.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			default:
				System.out.println("Invalid browser");
				return;
			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

		}

		if (po.getProperty("execution_env").equals("local")) {
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid Browser");
				return;
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		driver.get(po.getProperty("url"));
	}

	@AfterClass(groups = { "Sanity", "Regression" })
	public void teardown() {
		driver.quit();
	}

	public String getRandomString() {
		String random = RandomStringUtils.randomAlphabetic(6);
		return random;
	}

	public String getRandonNumber() {
		String random = RandomStringUtils.randomNumeric(6);
		return random;
	}

	public String getRandomPwd() {
		String random = RandomStringUtils.randomAlphabetic(4);
		String random2 = RandomStringUtils.randomNumeric(4);

		return (random + "$%" + random2);
	}

	public String captureScreenShot(String name) {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

		TakesScreenshot ts = (TakesScreenshot) driver;
		File sf = ts.getScreenshotAs(OutputType.FILE);
		String tfpath = System.getProperty("user.dir") + "\\screenshots\\" + name + "_" + timeStamp + ".png";

		File tf = new File(tfpath);
		sf.renameTo(tf);

		return tfpath;

	}
}
