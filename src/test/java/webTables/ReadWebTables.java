package webTables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReadWebTables {

	String url = "file:///C:/Users/Zghiban/eclipse-workspace/Selenium-maven-automation-MuradilClass/src/test/java/webTables/webPage.html";
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void readScores() {
		driver.get(url);
		// Read whole webTable data
		WebElement table = driver.findElement(By.tagName("tbody"));
		System.out.println(table.getText());

		// how many rows in the table??
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='worldcup']/tbody/tr"));
		System.out.println("number of data rows: " + rows.size());

		String headerPath = "//table[@id='worldcup']/thead/tr/th";

		List<WebElement> headers = driver.findElements(By.xpath(headerPath));

		List<String> expHeaders = Arrays.asList("Team1", "Score", "Team2");
		List<String> actHeaders = new ArrayList<>();
		for (WebElement h : headers) {
			actHeaders.add(h.getText());
		}

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actHeaders, expHeaders);

		String egyptXp = driver.findElement(By.xpath("/html/body/table/tbody/tr[3]/td[3]")).getText();
		System.out.println(egyptXp);

		softAssert.assertEquals(egyptXp, "Egypt");

		int rowsCount = driver.findElements(By.xpath("//table[@id='worldcup']/tbody/tr")).size();
		int colsCount = driver.findElements(By.xpath("//table[@id='worldcup']/thead/tr/th")).size();
		System.out.println("------------------------------------------------------");

		for (int rowNr = 1; rowNr <= rowsCount; rowNr++) {
			for (int col = 1; col <= colsCount; col++) {

				String xPath = "//table[@id='worldcup']/tbody/tr[" + rowNr + "]/td[" + col + "]";
				String tdData = driver.findElement(By.xpath(xPath)).getText();
				System.out.print(tdData + "  \t");
			}
			System.out.println();
		}
		softAssert.assertAll();
	}
	
	@Test
	public void applicantsData() {
		driver.get("https://forms.zohopublic.com/murodil/report/Applicants/reportperma/DibkrcDh27GWoPQ9krhiTdlSN4_34rKc8ngubKgIMy8");
		
		printTableData("reportTab");
	
	}
	
	public void printTableData(String id) {
		int rowsCount = driver.findElements(By.xpath("//table[@id='"+id+"']/tbody/tr")).size();
		int colsCount = driver.findElements(By.xpath("//table[@id='"+id+"']/thead/tr/th")).size();
		
		System.out.println("===============");
		
		for(int rowNum = 1; rowNum <= rowsCount; rowNum++) {
			for(int col = 1; col <= colsCount; col++) {
				String xpath = "//table[@id='"+id+"']/tbody/tr["+rowNum+"]/td["+col+"]";
				String tdData = driver.findElement(By.xpath(xpath)).getText();
				System.out.print(tdData +"   \t");
			}
			System.out.println();
		}
	}
	
	

	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
