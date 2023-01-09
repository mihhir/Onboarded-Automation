package Onboarded.Automation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport {

	public static WebDriver driver;
	public static ExtentTest SmokeTest;
	public static ExtentReports extent;
	public static ExtentHtmlReporter htmlreporter;
	public static String time;
	public static String ReportName;

	@BeforeSuite
	public static void setUp() {
		DateFormat dateFormat = new SimpleDateFormat(" dd-mm-yyyy.hh.mm.ss");
		Date date = new Date();

		time = dateFormat.format(date);
		ReportName = "Final Report" + time + ".html";
		htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/" + ReportName);

		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {
			String screenShotPath = null;
			SmokeTest.fail(MarkupHelper.createLabel(result.getName() + "Test Case Fail", ExtentColor.RED));
			System.out.print(result);
			SmokeTest.fail(result.getThrowable());
			// test.fail(MarkupHelper.createLabel(screenShotPath, ExtentColor.RED));
			SmokeTest.fail(screenShotPath, MediaEntityBuilder
					.createScreenCaptureFromPath(GetScreenShot.capture(driver, result.getName(), time)).build());

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			SmokeTest.pass(MarkupHelper.createLabel(result.getName() + "Test Case Pass", ExtentColor.GREEN));

		} else {
			SmokeTest.skip(MarkupHelper.createLabel(result.getName() + "Test Case Skipped", ExtentColor.YELLOW));
			SmokeTest.skip(result.getThrowable());
		}
	}

	@AfterSuite
	public static void tearDown() {
		extent.flush();
	}
}