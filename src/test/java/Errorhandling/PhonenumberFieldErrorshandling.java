package Errorhandling;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.BaseTest;

public class PhonenumberFieldErrorshandling extends BaseTest {

	String month = "June 2023";
	String day = "10";
	
	@Test(priority = 1)
	public void ScrollingIntoFormPage() {

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		WebElement form_page = driver.findElement(By.xpath("//*[@id='root']/div[2]/div/div[4]/div/div/div[3]/button"));
		js.executeScript("arguments[0].scrollIntoView(true);", form_page);

		driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div/div[3]/button")).click();
		Assert.assertTrue(
				driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[2]/div")).isDisplayed(),
				"The hotel booking form page is not opened");
		WebElement firstnameinputfield = driver
				.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[4]/div/div[2]/div[3]/div[1]/input"));
		js.executeScript("arguments[0].scrollIntoView(true);", firstnameinputfield);

	}
	
	@Test(priority = 2)
	public void InputFiledsErrors() throws Exception {
		
		//month and year selection
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		WebElement MonthandYear = driver
				.findElement(By.xpath("//*[@id='root']/div[2]/div/div[4]/div/div[2]/div[2]/div/div[1]/span[2]"));
		js.executeScript("arguments[0].scrollIntoView(true);", MonthandYear);
		while (true) {

			String month_Year = driver
					.findElement(By.xpath("//*[@id='root']/div[2]/div/div[4]/div/div[2]/div[2]/div/div[1]/span[2]"))
					.getText();

			if (month_Year.equals(month)) {
				System.out.println(month_Year);
				break;
			} else {
				driver.findElement(
						By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[2]/div/div[1]/span[1]/button[3]"))
						.click();
			}
		}
		
		//selecting dates
		
		List<WebElement> alldays = driver.findElements(
				By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/button"));

		for (WebElement ele : alldays) {

			String date_text = ele.getText();
			System.out.println(date_text);

			if (date_text.equals(day)) {

				WebElement date_start = driver.findElement(By
						.xpath("//*[@id='root']/div[2]/div/div[4]/div/div[2]/div[2]/div/div[2]/div[4]/div[1]/div[3]"));
				Actions action = new Actions(driver);
				action.click(ele).perform();
				action.clickAndHold().dragAndDropBy(date_start, 0, 30).release().perform();
				WebElement totaldays = driver.findElement(By.xpath(
						"//*[@id=\"root\"]/div[2]/div/div[4]/div/div[2]/div[2]/div/div[2]/div[4]/div[2]/div[2]/div[2]/div/div"));
				System.out.println("The totala days and total Amout is: " + totaldays.getText());
				break;
			}
		}	
	}

	@Test(priority = 3)
	public void PhoneNumberError1() throws Exception {
		driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[3]/div[1]/input"))
		.sendKeys("TESTSAMPLE123");
		driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[3]/div[2]/input")).sendKeys("TEST671");
		driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[3]/div[3]/input")).sendKeys("abcdef@gmail.com");
		//driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[3]/div[4]/input")).sendKeys("01531231231");
		driver.findElement(By.xpath("//*[@id='root']/div[2]/div/div[4]/div/div[2]/div[3]/button[2]")).click();
		String ActualErrorMessage = driver
				.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[4]/div/div[2]/div[3]/div[5]")).getText();
		System.out.println("The Error message is: " + ActualErrorMessage);
		String ExpectedErrorMessage = "must not be empty\r\n"
				+ "size must be between 11 and 21";
		softAssert.assertEquals(ActualErrorMessage, ExpectedErrorMessage,
				"Email Field error message displayed is wrongly");	
	}
	
	@Test(priority = 4)
	public void PhoneNumberError2() throws Exception {
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[3]/div[4]/input")).sendKeys("123123312");
		driver.findElement(By.xpath("//*[@id='root']/div[2]/div/div[4]/div/div[2]/div[3]/button[2]")).click();
		Thread.sleep(5000);
	}
}
