package POMdesign;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.webOrdersLogInPage;

public class webOrdersLogInTests {
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Ignore
	@Test
	public void positiveLogInTest() {
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
		driver.findElement(By.id("ctl00_MainContent_login_button")).click();
	}
	
	@Test(priority = 2)
	public void positiveLogInUsingPOM() { 
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		webOrdersLogInPage loginPage = new webOrdersLogInPage(driver);
		loginPage.userName.sendKeys("Tester");
		loginPage.password.sendKeys("test");
		loginPage.loginButton.click();
	}
	
	@Test(priority = 1)
	public void invalidUsernameTest() {
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		webOrdersLogInPage loginPage = new webOrdersLogInPage(driver);
		loginPage.userName.sendKeys("Invalid");
		loginPage.password.sendKeys("test");
		loginPage.loginButton.click();
		
		String errMsg = loginPage.invalidUserName.getText();
		assertEquals(errMsg, "Invalid Login or Password");
		
	}
	
	
	
	
	@AfterClass
	public void closure() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

}
