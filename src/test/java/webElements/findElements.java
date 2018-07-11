package webElements;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class findElements {
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().fullscreen();
		driver.findElement(By.cssSelector("#oracletaleocwsv2-wrapper > section.oracletaleocwsv2-navigation > div > nav > div > div:nth-child(1) > a"));

	}

	@Test
	public void SeleniumWebElementsForm() {
		driver.get(
				"https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg");

		List<WebElement> textBox = driver.findElements(By.xpath("//input[@type='text']"));
		System.out.println("number of text Boxes: " + textBox.size());

		List<WebElement> Dropdown = driver.findElements(By.tagName("select"));
		System.out.println("number of dropdowns: " + Dropdown.size());

		List<WebElement> Checkboxes = driver.findElements(By.xpath("//input[@elname='MatrixChoice']"));
		System.out.println("number of Checkboxes: " + Checkboxes.size());

		List<WebElement> Radios = driver.findElements(By.xpath("//input[@elname='MatrixChoice1']"));
		System.out.println("number of Radios: " + Radios.size());

		List<WebElement> Buttons = driver.findElements(By.xpath("//button"));
		System.out.println("Number of Buttons: " + Buttons.size());

		assertEquals(textBox.size(), 2);
		assertEquals(Dropdown.size(), 3);
		assertEquals(Checkboxes.size(), 9);
		assertEquals(Radios.size(), 9);
		assertEquals(Buttons.size(), 1);
	}

	@Test(priority = 1)
	public void slideShow() throws InterruptedException {

		driver.get("https://www.hbloom.com/Gifts/birthday-flowers");
		List<WebElement> images = driver.findElements(By.tagName("img"));
		List<String> srcs = new ArrayList<>();
		for (WebElement webElement : images) {
			srcs.add(webElement.getAttribute("src"));

		}
		for (String link : srcs) {
			driver.get(link);
			Thread.sleep(1234);
		}

		/*
		 * homework: Loop trough each input box and enter random names Loop trough each
		 * dropdown and randomly select by index. Loop trough each checkbox and select
		 * all of them Loop trough each radio Button and click one by one by waiting one
		 * second. Buttons - click them all!
		 */

	}

}
