package Workbook;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import common.BaseTest;
import common.TestNGListerner;
import io.github.bonigarcia.wdm.WebDriverManager;


public class pagevalidation {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://automationintesting.online/#/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String heading = driver.findElement(By.xpath("//*[@id='collapseBanner']/div/div[1]/div/h1")).getText();
		System.out.println(heading);

		String subheading = driver.findElement(By.xpath("//*[@id=\"collapseBanner\"]/div/div[1]/div/h4")).getText();
		System.out.println(subheading);

		SoftAssert softassert = new SoftAssert();

		// List all images on the page
		List<WebElement> allimages = driver.findElements(By.tagName("img"));
		System.out.println("The total number of images are " + allimages.size());
		for (int i = 0; i < allimages.size(); i++) {
			System.out.println("The image on the page are " + allimages.get(i).getAttribute("src"));

		}

		String Exploartionimage = driver.findElement(By.xpath("//*[@id=\"collapseBanner\"]/div/div[2]/div[1]/img"))
				.getAttribute("src");
		System.out.println("The Exploration image URL is " + Exploartionimage);

		// print image URL on the console
		String automation = driver.findElement(By.xpath("//*[@id=\"collapseBanner\"]/div/div[2]/div[2]/img"))
				.getAttribute("src");
		System.out.println("The automation image URL is " + automation);

		// Infrastructure Section

		// print image URL on the console
		String Infrastructure = driver.findElement(By.xpath("//*[@id=\"collapseBanner\"]/div/div[2]/div[3]/img"))
				.getAttribute("src");
		System.out.println("The Infrastructure image URL is " + Infrastructure);

		// Get Started section

		// print image URL on the console
		String GetStarted = driver.findElement(By.xpath("//*[@id=\"collapseBanner\"]/div/div[2]/div[4]/img"))
				.getAttribute("src");
		System.out.println("The Get started image URL is " + GetStarted);

		softassert.assertAll();

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		//js.executeScript("Window.scrollBy(0,1000)", "");
		
		WebElement scroll = driver.findElement(By.xpath("//*[@id='collapseBanner']/div/div[3]/div[2]/button"));
		
		
		js.executeScript("arguments[0].scrollIntoView(true);", scroll);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id='collapseBanner']/div/div[3]/div[2]/button"))).click();
		
		//driver.findElement(By.xpath("//*[@id='collapseBanner']/div/div[3]/div[2]/button")).click();
		driver.close();
	}
}
