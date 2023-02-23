package Onboarded.Automation;

import java.util.ArrayList;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestCaseFunction extends BetaClientsLogin {

//public class TestCaseFunction extends ProdClientsLogin {

//public class TestCaseFunction extends LiteClientsLogin {

	// public static WebDriver driver;
	// public static WebDriverWait wait;
	// public static ArrayList < String > clients;
	public static ArrayList<String> tabs;
	// String ClientName = clients.get(0);

	@Test(priority = 1, groups = "main")
	public void main() {
		BetaClientsLogin.LargeClients();
		// ProdClientsLogin.LargeClients();
		// LiteClientsLogin.LiteClients();
	}

	@Parameters({ "driver", "wait", "clients" })
	@Test(priority = 2, groups = "SmokeTest")
	public static void SmokeTest(WebDriver driver, Wait<WebDriver> wait, ArrayList<String> clients) {

		try {

			if (clients.size() > 0) {

				// calling a function to open client
				OpenClient(wait, clients);

				// Creating an array of active tabs in browser
				tabs = new ArrayList<String>();

				wait.until(ExpectedConditions.numberOfWindowsToBe(2));

				tabs.removeAll(tabs);
				tabs.addAll(0, (driver.getWindowHandles()));
				driver.switchTo().window(tabs.get(1));
				// System.out.printf("Title of this client website = " + driver.getTitle() +
				// "\n");

				// calling a function to open Onboarded Stage
				OnboardedStage(wait);
				// CompletedStage(wait);

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

				// driver.switchTo().window(tabs.get(0));
				// System.out.print("After switch windows ");
				// AdminLogout(wait);

				// driver.close();

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

	@Parameters({ "wait", "clients" })
	@Test(priority = 3, groups = "OpenClient")
	public static void OpenClient(Wait<WebDriver> wait, ArrayList<String> clients) {

		SmokeTest = extent.createTest("Open Client Test Case for " + clients.get(0));

		String ClientName = clients.get(0);

		// Clear previously entered client name from search box
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search By Client Name']")));
		WebElement ClearClientName = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//input[@placeholder='Search By Client Name']"));
			}
		});

		ClearClientName.clear();

		// Enter Client name in search box
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search By Client Name']")));
		WebElement SearchClient = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//input[@placeholder='Search By Client Name']"));
			}
		});

		SearchClient.sendKeys(ClientName);

		// CLick on Search button for search the client
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='search']")));
		WebElement SearchButton = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//button[@type='search']"));
			}
		});

		SearchButton.click();

		// Click on Redirect button to redirect to client site
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='fa fa-external-link']")));
		WebElement GotoClient = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//i[@class='fa fa-external-link']"));
			}
		});

		GotoClient.click();

	}

	@Parameters({ "wait" })
	@Test(priority = 4, groups = "OnboardedStage")
	public static void OnboardedStage(Wait<WebDriver> wait) {

		SmokeTest = extent.createTest("Go to Onboarded Stage Test Case for " + clients.get(0));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Onboarded']")));
		WebElement Onboarded = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//img[@alt='Onboarded']"));
			}
		});

		Onboarded.click();

	}

	@Parameters({ "wait" })
	@Test(priority = 4, groups = "CompletedStage")
	public static void CompletedStage(Wait<WebDriver> wait) {

		SmokeTest = extent.createTest("Go to Completed Stage Test Case for " + clients.get(0));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Completed']")));
		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Completed']")));
		WebElement Onboarded = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//img[@alt='Completed']"));
			}
		});

		Onboarded.click();

	}

	@Parameters({ "wait" })
	@Test(priority = 5, groups = "OpenCandidate")
	public static void OpenCandidate(Wait<WebDriver> wait) {

		SmokeTest = extent.createTest("Open Candidate Test Case for " + clients.get(0));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@tiptrigger='hover']")));
		WebElement Candidate = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//span[@tiptrigger='hover']"));
			}
		});

		Candidate.click();

	}

	@Parameters({ "driver", "wait", "clients" })
	@Test(priority = 6, groups = "PrintCandidateID")
	public static void PrintCandidateID(WebDriver driver, Wait<WebDriver> wait, ArrayList<String> clients) {

		SmokeTest = extent.createTest("Print Candidate ID Test Case for " + clients.get(0));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h3[@class='title current'])[last()]")));
		WebElement CandidateID = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("(//h3[@class='title current'])[last()]"));
			}
		});
		String heading = CandidateID.getText();
		System.out.println("Client Name: " + clients.get(0));
		System.out.println(heading);
	}

	@Parameters({ "driver", "wait" })
	@Test(priority = 7, groups = "ScrollUpDown")
	public static void ScrollUpDown(WebDriver driver, Wait<WebDriver> wait) {

		SmokeTest = extent.createTest("Scroll up and down Test Case for " + clients.get(0));

		// Scroll Down
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//img[@src='../../../assets/img/applicant-icon.png']")));
		WebElement ScrollDown = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//img[@src='../../../assets/img/applicant-icon.png']"));
			}
		});
		JavascriptExecutor scrolldown = (JavascriptExecutor) driver;
		scrolldown.executeScript("arguments[0].scrollIntoView();", ScrollDown);

		// Scroll Up
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='fa fa-chevron-up']")));
		WebElement ScrollUp = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//i[@class='fa fa-chevron-up']"));
			}
		});
		JavascriptExecutor scrollup = (JavascriptExecutor) driver;
		scrollup.executeScript("arguments[0].click()", ScrollUp);

	}

	@Parameters({ "driver", "wait" })
	@Test(priority = 8, groups = "MainPageClick")
	public static void MainPageClick(WebDriver driver, Wait<WebDriver> wait) {

		SmokeTest = extent.createTest("Go to Main Page Test Case for " + clients.get(0));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='img-responsive pointer']")));
		WebElement BackToMainPage = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//img[@class='img-responsive pointer']"));
			}
		});
		JavascriptExecutor backbutton = (JavascriptExecutor) driver;
		backbutton.executeScript("arguments[0].click()", BackToMainPage);

	}

	@Parameters({ "driver", "wait" })
	@Test(priority = 9, groups = "LogoutClient")
	public static void LogoutClient(WebDriver driver, Wait<WebDriver> wait) {

		SmokeTest = extent.createTest("Logout from Client Test Case for " + clients.get(0));

		// Logout Button Panel Click
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//img[@alt='Login'and(@src='assets/img/download.png')]")));
		WebElement Logoutbutton = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//img[@alt='Login'and(@src='assets/img/download.png')]"));
			}
		});
		JavascriptExecutor logoutbutton = (JavascriptExecutor) driver;
		logoutbutton.executeScript("arguments[0].click()", Logoutbutton);

		// Logout Button Click
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[@class='d-block mr-0'])[last()]")));
		WebElement Logout = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("(//li[@class='d-block mr-0'])[last()]"));
			}
		});
		JavascriptExecutor logout = (JavascriptExecutor) driver;
		logout.executeScript("arguments[0].click()", Logout);

	}

	@Parameters({ "wait" })
	@Test(priority = 10, groups = "AdminLogout")
	public static void AdminLogout(Wait<WebDriver> wait) {

		SmokeTest = extent.createTest("Admin Logout Test Case for " + clients.get(0));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class=\"logout\"]//span[2]/text()")));
		// WebElement AdminLogout = driver.findElement(By.xpath("//span[2]/text()"));
		WebElement AdminLogout = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//span[2]/text()"));
			}
		});
		// AdminLogout.click();
		JavascriptExecutor adminlogout = (JavascriptExecutor) driver;
		adminlogout.executeScript("arguments[0].click()", AdminLogout);

	}
}