package Onboarded.Automation;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BetaClientsLogin {

	public static WebDriver driver;
	public static Wait<WebDriver> wait;
	public static ArrayList<String> clients;
	public static ExtentTest SmokeTest;
	public static ExtentReports extent;
	public static ExtentHtmlReporter htmlreporter;
	public static String time;
	public static String ReportName;

	public static void BetaClients() {

		try {

			String baseUrl = "https://adminuat.onboarded.com.au/";

			System.setProperty("webdriver.chrome.driver", "test/resources/chromedriver.exe");

			// Web Driver created
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

			// got to given URL
			driver.get(baseUrl);
			driver.manage().window().maximize();

			// Fluent Wait Create
			wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(15))
					.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);

			// calling the method for Entering Credentials and signing in to Admin Panel
			Login();

			// Calling the method for create a clients array
			CreateClients();

			// Call our function/method here
			System.out.println(clients);
			TestCaseFunction.SmokeTest(driver, wait, clients);
			// End function/method here

		} catch (Exception e) {

			System.out.println("Error " + e);
			e.printStackTrace();
		}
	}

	public static void Login() {

		// SmokeTest = extent.createTest("Login Test Case");
		// Microsoft Logo Click
		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='signin-ms']"))).click();
		WebElement LogoClick = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//img[@alt='microsoft']"));
			}
		});

		LogoClick.click();

		// Type Username in Microsoft Login
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0116")));
		WebElement Username = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//input[@type='email']"));
				// wait.until(ExpectedConditions.presenceOfElementLocated(By.id("i0116")));
			}
		});

		Username.sendKeys("radixdt.2460@hotmail.com");

		// Next Button after Username
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idSIButton9")));
		WebElement NextButton = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.id("idSIButton9"));
			}
		});

		NextButton.click();

		// Type Password in Microsoft Login
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
		WebElement Password = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//input[@type='password']"));
			}
		});

		Password.sendKeys("Mihir27!");

		// Sign In button Click
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idSIButton9")));
		WebElement Signin = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.id("idSIButton9"));
			}
		});

		Signin.click();

		// Back Button Click
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='button']")));
		WebElement Back = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//input[@type='button']"));
			}
		});

		Back.click();

	}

	public static void CreateClients() {

		// SmokeTest = extent.createTest("Create Clients Test Case");

		clients = new ArrayList<String>();

		clients.add("Robert");
		clients.add("Talent");
		clients.add("Paxus");
		clients.add("Adecco");

	}

	@BeforeMethod
	public static void setUp() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy.hh.mm.ss");
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
			SmokeTest.fail(result.getThrowable());
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

	public static String capture(WebDriver driver, String stringDriver1, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat(" dd-mm-yyyy.hh.mm.ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);

		return destination;
	}

}