package navigatetosite;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import common.TestNGListerner;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.jodah.failsafe.internal.util.Assert;

//@Listeners(TestNGListerner.class)
public class lauchapplicaion {

	public WebDriver driver;
	String URL = "https://automationintesting.online/#/";
	SoftAssert softassert = new SoftAssert();

	@BeforeTest
	public void openbrowser() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test
	public void heading_and_subheading() {

		String heading = driver.findElement(By.xpath("//*[@id='collapseBanner']/div/div[1]/div/h1")).getText();
		System.out.println(heading);

		String subheading = driver.findElement(By.xpath("//*[@id=\"collapseBanner\"]/div/div[1]/div/h4")).getText();
		System.out.println(subheading);

	}

	@Test
	public void no_of_images() {
		List<WebElement> allimages = driver.findElements(By.tagName("img"));
		System.out.println("The total number of images are " + allimages.size());
		for (int i = 0; i < allimages.size(); i++) {
			System.out.println("The image on the page are " + allimages.get(i).getAttribute("src"));

		}
	}

	@Test(priority = 1)
	public void exploartion() {
		String Exploartionimage = driver.findElement(By.xpath("//*[@id=\"collapseBanner\"]/div/div[2]/div[1]/img"))
				.getAttribute("src");
		System.out.println("The Exploration image URL is " + Exploartionimage);
	}

	@Test(priority = 2)
	public void automation() {
		String automation = driver.findElement(By.xpath("//*[@id=\"collapseBanner\"]/div/div[2]/div[2]/img"))
				.getAttribute("src");
		System.out.println("The automation image URL is " + automation);

	}

	@Test(priority = 3)
	public void infrastructure() {
		String Infrastructure = driver.findElement(By.xpath("//*[@id=\"collapseBanner\"]/div/div[2]/div[3]/img"))
				.getAttribute("src");
		System.out.println("The Infrastructure image URL is " + Infrastructure);
	}

	@Test(priority = 4)
	public void Get_Started() {
		String GetStarted = driver.findElement(By.xpath("//*[@id=\"collapseBanner\"]/div/div[2]/div[4]/img"))
				.getAttribute("src");
		System.out.println("The Get started image URL is " + GetStarted);
		
	}

	@Test(priority = 5)
	public void scrorll_and_click() {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id='collapseBanner']/div/div[3]/div[2]/button"))).click();

		// driver.findElement(By.xpath("//*[@id='collapseBanner']/div/div[3]/div[2]/button")).click();
	}

	@AfterTest(enabled = true)
	public void closebrowser() {
		driver.close();
	}
}
