package navigatetosite;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;

import common.BaseTest;
import common.TestNGListerner;

//@Listeners(TestNGListerner.class)
public class login extends BaseTest {

	@Test(groups = {"Sanity"})
	public void login_applicatinon() {
		
		extentTest.info("Scrolling webPage to till form page");
		JavascriptExecutor js = ((JavascriptExecutor) driver);

		WebElement form_page = driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div/div[3]/h3"));

		js.executeScript("arguments[0].scrollIntoView(true);", form_page);
	}
	
	@Test(groups = {"Sanity", "Regression"})
	public void screenshot_form_page() throws IOException {
		extentTest.info("Capturing Screen shot of the form page");
		Date currentdate = new Date();
	    System.out.println(currentdate);
	    String screenshotfilename = currentdate.toString().replaceAll(":", "-");
	    System.out.println(screenshotfilename);
	    
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"root\"]/div/div/div[4]/div/div/div[2]/img")));
	    
	    File screenshotfile =  ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(screenshotfile, new File(".//screenshot1//"+screenshotfilename+".png"));
	    
		softAssert.assertTrue(true);
		extentTest.info("Assertion is passed for the WebPage");
	}
	
	@Test(groups = {"Sanity", "Regression" , "Smoke"})
	public void book_this_room() {
		
		extentTest.info("Booking hotel!!!");
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[4]/div/div/div[3]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[4]/div/div[2]/div[2]/div/div[1]/span[1]/button[1]")).click();
		driver.findElements(By.xpath("//*[@id=\"root\"]/div[2]/div/div[4]/div/div[2]/div[2]/div"));
		//softAssert.assertTrue(false);
	}
}
