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

import common.TestNGListerner;

//@Listeners(TestNGListerner.class)
public class login extends lauchapplicaion {

	@Test
	public void login_applicatinon() {
		
		JavascriptExecutor js = ((JavascriptExecutor) driver);

		WebElement form_page = driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div/div[3]/h3"));

		js.executeScript("arguments[0].scrollIntoView(true);", form_page);
	}
	
	@Test
	public void screenshot_form_page() throws IOException {
		Date currentdate = new Date();
	    System.out.println(currentdate);
	    String screenshotfilename = currentdate.toString().replaceAll(":", "-");
	    System.out.println(screenshotfilename);
	    
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"root\"]/div/div/div[4]/div/div/div[2]/img")));
	    
	    File screenshotfile =  ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(screenshotfile, new File(".//screenshot1//"+screenshotfilename+".png"));
	}
	
	@Test
	public void book_this_room() {
		
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[4]/div/div/div[3]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[4]/div/div[2]/div[2]/div/div[1]/span[1]/button[1]")).click();
		driver.findElements(By.xpath("//*[@id=\"root\"]/div[2]/div/div[4]/div/div[2]/div[2]/div"));
	}
}
