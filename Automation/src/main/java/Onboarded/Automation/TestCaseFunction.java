package Onboarded.Automation;

import java.util.ArrayList;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TestCaseFunction extends ExtentReport {

	// public static WebDriver driver;
	// public static WebDriverWait wait;
	// public static ArrayList < String > clients;
	public static ArrayList<String> tabs;
	// String ClientName = clients.get(0);
	public static WebDriverWait wait1;

	public static void SmokeTest(WebDriver driver, Wait<WebDriver> wait, ArrayList<String> clients) {

		try {

			if (clients.size() > 0) {

				// calling a function to open client
				OpenClient(wait, clients);

				// Creating an array of active tabs in browser
				CreateTabsArray(driver);

				// calling a function to open Onboarded Stage
				OnboardedStage(wait);

				// calling a function to Open Candidate
				OpenCandidate(wait);

				// calling a function to featch Candidate ID
				PrintCandidateID(driver, wait, clients);

				// calling a function to scroll page from top to end to top
				ScrollUpDown(driver, wait);

				// calling a function to go back to main page
				MainPageClick(driver, wait);

				// calling a function to logout from client
				LogoutClient(driver, wait);

				// Close the client tab
				driver.close();

				// swithing our driver to admin tab
				driver.switchTo().window(tabs.get(0));

				// remove tested client from array and starting test again for next client
				clients.remove(0);
				SmokeTest(driver, wait, clients);

			} else {
				System.out.println("Above clients are Tested.");

				// calling a function to Logout from Admin
				AdminLogout(driver, wait);

				driver.quit();
			}
		} catch (Exception e) {

			System.out.println("Client Name: " + clients.get(0) + " has encountered below error.\n");
			e.printStackTrace();

			driver.close();

			driver.switchTo().window(tabs.get(0));

			clients.remove(0);
			SmokeTest(driver, wait, clients);

		}
	}

	@Test
	public static void OpenClient(Wait<WebDriver> wait, ArrayList<String> clients) {

		String ClientName = clients.get(0);

		// Clear previously entered client name from search box
		WebElement ClearClientName = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//input[@placeholder='Search By Client Name']"));
			}
		});

		ClearClientName.clear();

		// Enter Client name in search box
		WebElement SearchClient = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//input[@placeholder='Search By Client Name']"));
			}
		});

		SearchClient.sendKeys(ClientName);

		// CLick on Search button for search the client
		WebElement SearchButton = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//button[@type='search']"));
			}
		});

		SearchButton.click();

		// Click on Redirect button to redirect to client site
		WebElement GotoClient = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//i[@class='fa fa-external-link']"));
			}
		});

		GotoClient.click();

	}

	@Test
	public static void CreateTabsArray(WebDriver driver) {

		ArrayList<String> tabs = new ArrayList<String>();

		wait1.until(ExpectedConditions.numberOfWindowsToBe(2));

		tabs.removeAll(tabs);
		tabs.addAll(0, (driver.getWindowHandles()));
		driver.switchTo().window(tabs.get(1));
		System.out.printf("Title of this client website = " + driver.getTitle() + '\n');

	}

	@Test
	public static void OnboardedStage(Wait<WebDriver> wait) {

		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Onboarded']"))).click();
		WebElement Onboarded = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//img[@alt='Onboarded']"));
			}
		});

		Onboarded.click();

	}

	@Test
	public static void OpenCandidate(Wait<WebDriver> wait) {

		// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@tiptrigger='hover']")))
		// .click();
		WebElement Candidate = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//span[@tiptrigger='hover']"));
			}
		});

		Candidate.click();

	}

	@Test
	public static void PrintCandidateID(WebDriver driver, Wait<WebDriver> wait, ArrayList<String> clients) {

		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h3[@class='title
		// current'])[last()]")));
		WebElement CandidateID = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("(//h3[@class='title current'])[last()]"));
			}
		});
		String heading = CandidateID.getText();
		System.out.println("Client Name: " + clients.get(0));
		System.out.println(heading);
	}

	@Test
	public static void ScrollUpDown(WebDriver driver, Wait<WebDriver> wait) {

		// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li/a[@class='custom-red-bg']")));
		// Scroll Down
		WebElement ScrollDown = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//li/a[@class='custom-red-bg']"));
			}
		});
		JavascriptExecutor scrolldown = (JavascriptExecutor) driver;
		scrolldown.executeScript("arguments[0].scrollIntoView();", ScrollDown);

		// Scroll Up
		WebElement ScrollUp = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//i[@class='fa fa-chevron-up']"));
			}
		});
		JavascriptExecutor scrollup = (JavascriptExecutor) driver;
		scrollup.executeScript("arguments[0].click()", ScrollUp);

	}

	@Test
	public static void MainPageClick(WebDriver driver, Wait<WebDriver> wait) {

		WebElement BackToMainPage = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//img[@class='img-responsive pointer']"));
			}
		});
		JavascriptExecutor backbutton = (JavascriptExecutor) driver;
		backbutton.executeScript("arguments[0].click()", BackToMainPage);

	}

	@Test
	public static void LogoutClient(WebDriver driver, Wait<WebDriver> wait) {

		// Logout Button Panel Click
		WebElement Logoutbutton = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//img[@alt='Login'and(@src='assets/img/download.png')]"));
			}
		});
		JavascriptExecutor logoutbutton = (JavascriptExecutor) driver;
		logoutbutton.executeScript("arguments[0].click()", Logoutbutton);

		// Logout Button Click
		WebElement Logout = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("(//li[@class='d-block mr-0'])[last()]"));
			}
		});
		JavascriptExecutor logout = (JavascriptExecutor) driver;
		logout.executeScript("arguments[0].click()", Logout);

	}

	@Test
	public static void AdminLogout(WebDriver driver, Wait<WebDriver> wait) {

		WebElement AdminLogout = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//span@class='icon-UserAccount mr-2'"));
			}
		});
		JavascriptExecutor adminlogout = (JavascriptExecutor) driver;
		adminlogout.executeScript("arguments[0].click()", AdminLogout);

	}
}