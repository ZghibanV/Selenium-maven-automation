package webElements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebElements {
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().fullscreen();
	}

	@Test
	public void myLinks() {
		driver.get("https://github.com");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		// how many links on github home page.
		int linksOnGithubHomePage = links.size();
		System.out.println("numner of links: " + linksOnGithubHomePage);
		List<String> strLinks = new ArrayList<>();
		for (WebElement webElement : links) {
			if (!webElement.getText().isEmpty()) {
				System.out.println(webElement.getText());				
				strLinks.add(webElement.getText());
			}
		}
		System.out.println(strLinks);

	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
	}
}
