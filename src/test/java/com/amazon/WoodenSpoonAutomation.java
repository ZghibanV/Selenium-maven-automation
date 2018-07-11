package com.amazon;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WoodenSpoonAutomation {
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void test() {
		driver.get("https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Daps&field-keywords=wooden+spoon");
		List<WebElement> wholeItems = driver.findElements(By.xpath("//div[@class='s-item-container']"));

		System.out.println("wholeItems.size():" + wholeItems.size());

		for (int i = 0; i < wholeItems.size(); i++) {
			if (wholeItems.get(i).getText().isEmpty())
				continue;

			String desXpath = "(//div[@class='s-item-container'])[" + (i + 1) + "]//h2";
			String priceXpath = "(//div[@class='s-item-container'])[" + (i + 1)
					+ "]//span[@class='sx-price sx-price-large']";

			System.out.println(driver.findElement(By.xpath(desXpath)).getText());
			System.out.println(driver.findElement(By.xpath(priceXpath)).getText());
			System.out.println("------------");

		}
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
