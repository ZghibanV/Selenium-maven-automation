package POMdesign;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AllOrdersPage;
import pages.ProductsPage;
import pages.webOrdersLogInPage;

public class AllOrdersTests {
	WebDriver driver;
	webOrdersLogInPage loginPage;
	AllOrdersPage allOrdersPage;
	String userid = "Tester";
	String password = "test";
	ProductsPage productsPage;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void setUpApplication() {
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		loginPage = new webOrdersLogInPage(driver);
	}

	@Test(description = "Verify that labels and tab links are displayed", priority = 1)
	public void labelsVerification() {
		String expected = "Web Orders Login";
		assertEquals(driver.getTitle(), expected, "LoginPage is not displayed. Application is down");
		/*
		 * loginPage.userName.sendKeys(userid); loginPage.password.sendKeys(password);
		 * loginPage.loginButton.click();
		 */
		loginPage.login(userid, password);

		allOrdersPage = new AllOrdersPage(driver);

		assertTrue(allOrdersPage.webOrders.isDisplayed(), "WebOrders are not displayed");

		assertTrue(allOrdersPage.listOfAllOrders.isDisplayed(), "List Of All Orders are not displayed");

		assertEquals(allOrdersPage.welcomeMsg.getText(), "Welcome, " + userid + "! | Logout");
		assertTrue(allOrdersPage.orderTab.isDisplayed(), "orderTab is not Displayed");
	}

	/*
	    Step 1. Navigate to loginpage
		Step 2. Assert that you are on loginpage
		Step 3. Login using valid credentials
		Step 4. Click on view all products
		Step 5. Verify following products are displayed:
				MyMoney
				FamilyAlbum
				ScreenSaver
		Step 6. Verify prices and discounts :
				MyMoney	    $100 8%
				FamilyAlbum	$80	15%
				ScreenSaver	$20	10%

	 */

	@Test(description = "Verify default Products and prices")
	public void availableProductsTest() {
		String expected = "Web Orders Login";
		assertEquals(driver.getTitle(), expected, "LoginPage is not displayed. Application is down");
		/*
		 * loginPage.userName.sendKeys(userid); loginPage.password.sendKeys(password);
		 * loginPage.loginButton.click();
		 */
		loginPage.login(userid, password);

		allOrdersPage = new AllOrdersPage(driver);

		allOrdersPage.viewAllProducts.click();
		productsPage = new ProductsPage(driver);

		List<String> expProducts = Arrays.asList("MyMoney", "FamilyAlbum", "ScreenSaver");
		List<String> actProducts = new ArrayList<>();

		productsPage.productNames.forEach(elem -> actProducts.add(elem.getText()));
		assertEquals(actProducts, expProducts);
		
		for (WebElement row : productsPage.productsRows) {
			
			if(row.getText().startsWith("MyMoney")) { 
				
				String[] prodData = row.getText().split(" ");
				switch(prodData[0]) {
				case "MyMoney":
					assertEquals(prodData[1], "$100");		
					assertEquals(prodData[2], "8%");	
					break;
				case "FamilyAlbum":
					assertEquals(prodData[1], "$80");		
					assertEquals(prodData[2], "15%");						
					break;
				case "ScreenSaver":
					assertEquals(prodData[1], "$20");		
					assertEquals(prodData[2], "10%");					
					break;
				}
				
			}
			System.out.println(row.getText());
		}

	}

	// logOut after each test:
	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
		allOrdersPage.logout();
	}

	@AfterClass
	public void closure() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

}
