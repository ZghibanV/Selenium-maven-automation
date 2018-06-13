package com.dice;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		//fulscreen
		driver.manage().window().fullscreen();
		// set universal wait time in case web page is slow.
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		/* Step 1. launch browser and  navigate to https:\\dice.com
		 * Expected : Dice home page comes up in the browser. fulscreen mode. 
		 */
		
		String url = "https:\\dice.com";
		driver.get(url);
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "Job Search for Technology Professionals | Dice.com";
		
		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Step PASS. Dice homepage succesifully laoded");
				
		}else {
			System.out.println("Step FAIL. Dice homepage not laoded");
			throw new RuntimeException("Step Fail. Dice homepage not loaded");
		}
		
		String keyword ="java developer";
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		
		String location = "22102";

		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		
		driver.findElement(By.id("findTechJobs")).click();
		
		String count = driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);
		int countResult = Integer.parseInt(count.replace(",", ""));
		
		if(countResult > 0) {
			System.out.println( "Step PASS: Keyword : " + keyword +" search returned " +
			countResult +" results in " + location);
		}else {
			System.out.println( "Step FAIL: Keyword : " + keyword +" search returned " +
					countResult +" results in " + location);
			}
		driver.close();
		System.out.println("Test completed -" + LocalDateTime.now());
		}
	}
