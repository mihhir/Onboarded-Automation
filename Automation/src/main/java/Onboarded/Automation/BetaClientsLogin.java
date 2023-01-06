package Onboarded.Automation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class BetaClientsLogin {

	public static WebDriver driver;
	public static Wait<WebDriver> wait;
	public static ArrayList<String> clients;
	// public static ArrayList < String > tabs;
	// String ClientName = clients.get(0);

	public static TestCaseFunction SmokeTest = new TestCaseFunction();
	public static ExtentReport getResult = new ExtentReport();

	@BeforeSuite
	public void main() {

		try {

			String baseUrl = "https://adminuat.onboarded.com.au/";

			System.setProperty("webdriver.chrome.driver", "test/resources/chromedriver.exe");

			// Web Driver created
			driver = new ChromeDriver();

			// got to given URL
			driver.get(baseUrl);
			driver.manage().window().maximize();

			// Fluent Wait Create
			wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
					.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);

			// calling the method for Entering Credentials and signing in to Admin Panel
			Login();

			// Calling the method for create a clients array
			CreateClients();

			// Call our function/method here
			System.out.println(clients);
			TestCaseFunction.SmokeTest(driver, wait, clients);
			// End function/method here

		} catch (Exception e) {

			System.out.println(e);
		}
	}

	@Test
	public static void Login() {

		// Microsoft Logo Click
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='microsoft']")));
		WebElement LogoClick = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//img[@alt='microsoft']"));
			}
		});

		// xPath = driver.findElement(By.xpath("//img[@alt='microsoft']"));
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

		Password.sendKeys("Mihir26!");

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

	@Test
	public static void CreateClients() {

		clients = new ArrayList<String>();

		clients.add("Robert");
		clients.add("Talent");
		clients.add("Paxus");
		clients.add("Adecco");

	}

}