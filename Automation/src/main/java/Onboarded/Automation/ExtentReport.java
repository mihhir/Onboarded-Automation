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
	public static ExtentTest test;
	public static ExtentReports extent;
	public static ExtentHtmlReporter htmlreporter;
	public static WebDriver driver;
	public static String screenShotName;

	@BeforeSuite
	public static void setUp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date date = new Date();

		String time = dateFormat.format(date);
		String ReportName = "Final Report" + time + ".html";
		htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/" + ReportName);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {
			// String screenShotPath = GetScreenShot.capture(driver, "screenShotName");
			String screenShotPath = null;
			test.fail(MarkupHelper.createLabel(result.getName() + "Test Case Fail", ExtentColor.RED));
			System.out.print(result);
			test.fail(result.getThrowable());
			//test.fail(MarkupHelper.createLabel(screenShotPath, ExtentColor.RED));
			test.fail(screenShotPath, MediaEntityBuilder.createScreenCaptureFromPath(GetScreenShot.capture(driver, result.getName())).build());

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.pass(MarkupHelper.createLabel(result.getName() + "Test Case Pass", ExtentColor.GREEN));

		} else {
			test.skip(MarkupHelper.createLabel(result.getName() + "Test Case Skipped", ExtentColor.YELLOW));
			test.skip(result.getThrowable());
		}
	}

	@AfterSuite
	public static void tearDown() {
		extent.flush();
		driver.close();
	}
}