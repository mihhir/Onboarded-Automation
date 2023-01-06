package Onboarded.Automation;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class TestCaseFunction {
	
	//public static WebDriver driver;
    //public static WebDriverWait wait;
    //public static ArrayList < String > clients;
    public static ArrayList < String > tabs;
    //String ClientName = clients.get(0);

    
    public static void SmokeTest(WebDriver driver, Wait < WebDriver > wait, ArrayList < String > clients) {
    	
		
        try {

            if (clients.size() > 0) {
                String ClientName = clients.get(0);

                
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Search By Client Name']"))).clear();
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Search By Client Name']"))).sendKeys(ClientName);
                //WebElement SearchClient = driver.findElement(By.xpath("//input[@placeholder='Search By Client Name']"));
                //SearchClient.sendKeys("Robert");
                //JavascriptExecutor searchclient = ((JavascriptExecutor) driver);
                //searchclient.executeScript("arguments[0].value=" + clients.get(0) + ";", SearchClient);

                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='search']"))).click();
                //WebElement Search = driver.findElement(By.xpath("//button[@type='search']"));
                //Search.click();

                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[@class='fa fa-external-link']"))).click();
                //WebElement GotoClient = driver.findElement(By.xpath("//i[@class='fa fa-external-link']"));
                //GotoClient.click();

                ArrayList < String > tabs = new ArrayList < String > ();

                wait.until(ExpectedConditions.numberOfWindowsToBe(2));

                tabs.removeAll(tabs);
                tabs.addAll(0, (driver.getWindowHandles()));
                driver.switchTo().window(tabs.get(1));
                System.out.printf("Title of this client website = " + driver.getTitle() + "/n");

                try {
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Onboarded']"))).click();

                } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Onboarded']"))).click();

                }

                // Open Candidate
                try {
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@tiptrigger='hover']"))).click();

                } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@tiptrigger='hover']"))).click();

                }

                // Featching Candidate ID
                try {
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//h3[@class='title current'])[last()]")));
                    String heading = driver.findElement(By.xpath("(//h3[@class='title current'])[last()]")).getText();
                    System.out.println("Client Name: " + clients.get(0));
                    System.out.println(heading);

                } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//h3[@class='title current'])[last()]")));
                    String heading = driver.findElement(By.xpath("(//h3[@class='title current'])[last()]")).getText();
                    System.out.println(heading);

                }

                // Scroll page to end
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li/a[@class='custom-red-bg']")));
                WebElement Finding3 = driver.findElement(By.xpath("//li/a[@class='custom-red-bg']"));
                JavascriptExecutor Scroll3 = (JavascriptExecutor) driver;
                Scroll3.executeScript("arguments[0].scrollIntoView();", Finding3);

                // Scroll page to top
                WebElement Scrollup = driver.findElement(By.xpath("//i[@class='fa fa-chevron-up']"));
                JavascriptExecutor scrollup = (JavascriptExecutor) driver;
                scrollup.executeScript("arguments[0].click()", Scrollup);

                // Back to main page
                try {

                    WebElement Backbutton = driver.findElement(By.xpath("//img[@class='img-responsive pointer']"));
                    JavascriptExecutor backbutton = (JavascriptExecutor) driver;
                    backbutton.executeScript("arguments[0].click()", Backbutton);

                } catch (org.openqa.selenium.StaleElementReferenceException ex) {

                    WebElement Backbutton = driver.findElement(By.xpath("//img[@class='img-responsive pointer']"));
                    JavascriptExecutor backbutton = (JavascriptExecutor) driver;
                    backbutton.executeScript("arguments[0].click()", Backbutton);
                    //System.out.println(ex);
                }

                // Open Logout panel
                try {
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='Login'and(@src='assets/img/download.png')]")));
                    WebElement Logoutbutton = driver.findElement(By.xpath("//img[@alt='Login'and(@src='assets/img/download.png')]"));
                    JavascriptExecutor logoutbutton = (JavascriptExecutor) driver;
                    logoutbutton.executeScript("arguments[0].click()", Logoutbutton);

                } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='Login'and(@src='assets/img/download.png')]")));
                    WebElement Logoutbutton = driver.findElement(By.xpath("//img[@alt='Login'and(@src='assets/img/download.png')]"));
                    JavascriptExecutor logoutbutton = (JavascriptExecutor) driver;
                    logoutbutton.executeScript("arguments[0].click()", Logoutbutton);

                }

                // Logout
                try {

                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//li[@class='d-block mr-0'])[last()]")));
                    WebElement Logout = driver.findElement(By.xpath("(//li[@class='d-block mr-0'])[last()]"));
                    JavascriptExecutor logout = (JavascriptExecutor) driver;
                    logout.executeScript("arguments[0].click()", Logout);

                } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//li[@class='d-block mr-0'])[last()]")));
                    WebElement Logout = driver.findElement(By.xpath("(//li[@class='d-block mr-0'])[last()]"));
                    JavascriptExecutor logout = (JavascriptExecutor) driver;
                    logout.executeScript("arguments[0].click()", Logout);
                }

                driver.close();

                driver.switchTo().window(tabs.get(0));

                clients.remove(0);
                SmokeTest(driver, wait, clients);

            } else {
                System.out.println("Above clients are Tested.");

                try {
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span@class='icon-UserAccount mr-2'")));
                    WebElement AdminLogout = driver.findElement(By.xpath("(//span@class='icon-UserAccount mr-2'"));
                    JavascriptExecutor adminlogout = (JavascriptExecutor) driver;
                    adminlogout.executeScript("arguments[0].click()", AdminLogout);

                } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span@class='icon-UserAccount mr-2'")));
                    WebElement AdminLogout = driver.findElement(By.xpath("(//span@class='icon-UserAccount mr-2'"));
                    JavascriptExecutor adminlogout = (JavascriptExecutor) driver;
                    adminlogout.executeScript("arguments[0].click()", AdminLogout);
                }

                driver.quit();
            }
        } catch (Exception e) {

            System.out.println("Client Name: " + clients.get(0) + " has encountered below error.");
            e.printStackTrace();

            // SendMailFunction();

            //System.out.println("/n");

            driver.close();

            driver.switchTo().window(tabs.get(0));

            clients.remove(0);
            SmokeTest(driver, wait, clients);

        }
    }

}