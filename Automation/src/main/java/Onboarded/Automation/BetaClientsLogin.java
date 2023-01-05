package Onboarded.Automation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BetaClientsLogin {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static ArrayList < String > clients;
    //public static ArrayList < String > tabs;
    //String ClientName = clients.get(0);

    public static TestCaseFunction SmokeTest = new TestCaseFunction();

    public static void main(String[] args) {

        try {

            String baseUrl = "https://adminuat.onboarded.com.au/";

            System.setProperty("webdriver.chrome.driver", "test/resources/chromedriver.exe");

            // Web Driver created
            driver = new ChromeDriver();

            // got to given URL
            driver.get(baseUrl);
            driver.manage().window().maximize();
            //driver.getTitle();
            System.out.println(driver.getTitle());

            System.out.println(driver.getTitle());
            System.out.println(driver.getTitle());
            System.out.println(driver.getTitle());
            
            Wait < WebDriver > wait = new FluentWait < WebDriver > (driver)
                    .withTimeout(Duration.ofSeconds(20))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(NoSuchElementException.class);

            //Enter Credentials and sign in
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='microsoft']"))).click();
            //WebElement MS = driver.findElement(By.xpath("//img[@alt='microsoft']"));
            //MS.click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("i0116"))).sendKeys("radixdt.2460@hotmail.com");
            //WebElement Username = driver.findElement(By.id("i0116"));
            //Username.sendKeys("radixdt.2460@hotmail.com");

            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("idSIButton9"))).click();
            //WebElement Next = driver.findElement(By.id("idSIButton9"));
            //Next.click();

            Thread.sleep(5000);
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='password']"))).sendKeys("Mihir26!");
                //WebElement Password = driver.findElement(By.id("i0118"));
                //Password.sendKeys("w115C*COIrTg");

            } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("i0118"))).sendKeys("Mihir26!");
                //WebElement Password = driver.findElement(By.id("i0118"));
                //Password.sendKeys("w115C*COIrTg");

            }

            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("idSIButton9"))).click();
                //WebElement Signin = driver.findElement(By.id("idSIButton9"));
                //Signin.click();

            } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("idSIButton9"))).click();
                //WebElement Signin = driver.findElement(By.id("idSIButton9"));
                //Signin.click();

            }

            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='button']"))).click();
                //WebElement Backagain = driver.findElement(By.xpath("//input[@type='button']"));
                //Backagain.click();

            } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='button']"))).click();
                //WebElement Backagain = driver.findElement(By.xpath("//input[@type='button']"));
                //Backagain.click();
                //System.out.println(ex);

            }

            
        	clients = new ArrayList < String > ();

            clients.add("Robert");
            clients.add("Talent");
            clients.add("Paxus");
            clients.add("Adecco");
            
            // Call our function/method here

            System.out.println(clients);
            
            TestCaseFunction.SmokeTest(driver, wait, clients);

            // End function/method here

        } catch (Exception e) {

            System.out.println(e);
        }
    }

}