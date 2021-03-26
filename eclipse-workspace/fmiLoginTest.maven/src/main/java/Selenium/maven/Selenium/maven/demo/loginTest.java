//https://www.journaldev.com/25837/download-selenium-jars-configure-eclipse
package Selenium.maven.Selenium.maven.demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.TestNG;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class loginTest 
{
	private static void seleniumActions() {
	}
	
	@Test
	public static void chromeWDTest() throws InterruptedException, MalformedURLException
    {
//        ChromeOptions chromeOptions = new ChromeOptions();
//        RemoteWebDriver driver=new RemoteWebDriver(new URL("http://ec2-3-142-238-242.us-east-2.compute.amazonaws.com:4444/wd/hub"), chromeOptions);
		System.setProperty("webdriver.chrome.driver", "/Users/pchin/git/pchin13-qa/FMI/chromedriver");
		WebDriver driver=new ChromeDriver();
		
		// Apply wait time and maximize window size
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// Open browser to reach URL
		driver.get("https://www.linkedin.com/login");

        // Login by supplying username and password
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("pchin13@azeotropic.net");
		WebElement passwd = driver.findElement(By.id("password"));
		passwd.sendKeys("this1sATe$tAccount");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		// Search for Foundation Medicine in search bar
		WebElement searchBar = driver.findElement(By.cssSelector("input[placeholder='Search']"));
		searchBar.click();
		searchBar.sendKeys("Foundation Medicine");
		searchBar.sendKeys(Keys.ENTER);
//		searchBar.submit();
		
		// Click on FMI profile, then look for jobs
		driver.findElement(By.linkText("Foundation Medicine")).click();
		TimeUnit.SECONDS.sleep(2);
		
		// Test to make sure we get expected text in the jobs tab
		String expectedValue = "Jobs";
		WebElement findJobs = driver.findElement(By.xpath("//*[@href='/company/foundation-medicine/jobs/']"));
		System.out.println("actual text: " + findJobs.getText());
		findJobs.click();
        Assert.assertEquals(expectedValue, findJobs.getText());
		TimeUnit.SECONDS.sleep(3);

		// Logout of LinkedIn account
		driver.findElement(By.cssSelector("button[data-control-name='nav.settings']")).click();
		driver.findElement(By.xpath("//*[@href='/m/logout/']")).click();
	
		// Close the browser and quit
		driver.quit();
    }
	
	@Test
	public static void firefoxRemoteWDTest() throws InterruptedException, MalformedURLException
    {
//		  System.setProperty("webdriver.gecko.driver","/Users/pchin/git/pchin13-qa/FMI/geckodriver");
//		  WebDriver driver=new FirefoxDriver();
//		String nodeURL = "http://127.0.0.1:4444/wd/hub";
		String nodeURL = "http://ec2-3-142-238-242.us-east-2.compute.amazonaws.com:4444/wd/hub";
        FirefoxOptions ffOptions = new FirefoxOptions();
        RemoteWebDriver driver=new RemoteWebDriver(new URL(nodeURL), ffOptions);
		
		// Apply wait time and maximize window size
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// Open browser to reach URL
		driver.get("https://www.linkedin.com/login");
		TimeUnit.SECONDS.sleep(2);
		
		// Test to make sure we get expected text
		String expectedValue = "Sign in";
		String actualValue = driver.findElement(By.className("header__content__heading")).getText();
        System.out.println("text: " + actualValue);
        Assert.assertEquals(expectedValue, actualValue);
	
		// Close the browser and quit else the browser session stays connected to the grid.
		driver.quit();
    }
}
