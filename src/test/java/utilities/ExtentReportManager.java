package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkrepoter;
	public ExtentReports reports;
	public ExtentTest test;

	String repname;

	@Override
	public void onStart(ITestContext testContext) {

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		repname = "TestReport -" + timeStamp + ".html";

		sparkrepoter = new ExtentSparkReporter(".\\reports\\" + repname);

		sparkrepoter.config().setDocumentTitle("OpenCart Automation Report");
		sparkrepoter.config().setReportName("OpenCart Functional Testing");
		sparkrepoter.config().setTheme(Theme.DARK);

		reports = new ExtentReports();
		reports.attachReporter(sparkrepoter);

		reports.setSystemInfo("Application", "OpenCart");
		reports.setSystemInfo("Module", "Admin");
		reports.setSystemInfo("SubModule", "Customer");
		reports.setSystemInfo("User Name", System.getProperty("user.name"));

		String os = testContext.getCurrentXmlTest().getParameter("os");
		reports.setSystemInfo("operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("Browser");
		reports.setSystemInfo("Browser", browser);

		List<String> inculdedgroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!inculdedgroups.isEmpty()) {
			reports.setSystemInfo("Group", inculdedgroups.toString());
		}

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test = reports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "Test Case Passed " + result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test = reports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Case Skipped " + result.getName());
		test.log(Status.INFO, result.getThrowable().getMessage());

	}

	@Override
	public void onTestFailure(ITestResult result) {
		test = reports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Case Failed " + result.getName());
		test.log(Status.INFO, result.getThrowable().toString());

		try {
			String imgpath = new BaseClass().captureScreenShot(result.getName());
			test.addScreenCaptureFromPath(imgpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onFinish(ITestContext context) {

		reports.flush();

		String repath = System.getProperty("user.dir") + "\\reports\\" + repname;
		File extentreport = new File(repath);

		try {
			Desktop.getDesktop().browse(extentreport.toURI());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
