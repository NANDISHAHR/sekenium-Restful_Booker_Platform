package Utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import navigatetosite.printalllinks;


public class Screenshotfirstpage extends printalllinks {

	@Test(priority = 3)
	public void screenshottaken() throws IOException {
	Date currentdate = new Date();
    System.out.println(currentdate);
    String screenshotfilename = currentdate.toString().replaceAll(":", "-");
    System.out.println(screenshotfilename);
    
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"collapseBanner\"]/div/div[1]/div/h1")));
    
    File screenshotfile =  ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(screenshotfile, new File(".//screenshot//"+screenshotfilename+".png"));
    		
}
}
