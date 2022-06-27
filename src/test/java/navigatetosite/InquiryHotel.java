package navigatetosite;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import common.BaseTest;

public class InquiryHotel extends BaseTest {


	@Test
	public void ScrollIntoForm2() throws Exception {

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		WebElement addressOfHotel = driver.findElement(By.xpath("//*[@id=\"name\"]"));
		js.executeScript("arguments[0].scrollIntoView(true);", addressOfHotel);

		String Hotelinfo = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[5]/div[3]")).getText();
		System.out.println("The hotel information is: "+Hotelinfo);

		driver.findElement(By.id("name")).sendKeys("TESTER");
		driver.findElement(By.id("email")).sendKeys("abcde@gmail.com");
		driver.findElement(By.id("phone")).sendKeys("1231231231231");
		driver.findElement(By.id("subject")).sendKeys("Book an inquiry about Hotel");
		String message = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[5]/div[2]/form/div[5]/div/span"))
				.getText();
		System.out.println("The message text field name is: " + message);
		driver.findElement(By.id("description")).sendKeys(
				"What is the cost of leaving for 10 days in your hotel and rooms are availble for next month starting of first week");
		driver.findElement(By.id("submitContact")).click();

		
		/*
		 * WebElement form_page = driver.findElement(By.xpath(
		 * "//*[@id='root']/div/div/div[4]/div/div/div[3]/h3"));
		 * js.executeScript("arguments[0].scrollIntoView(true);", form_page);
		 */
		driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div/div[3]/button")).click();
		String message1 = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[5]/div[2]/div/h2")).getText();
		System.out.println(message1);
		String message2 = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[5]/div[2]/div/p[1]")).getText();
		System.out.println(message2);
		String ActualName = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[5]/div[2]/div/p[2]"))
				.getText();
		System.out.println(ActualName);
		String message4 = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[5]/div[2]/div/p[3]")).getText();
		System.out.println(message4);

		
		/*
		 * Thread.sleep(5000); WebElement button = driver .findElement(By.xpath(
		 * "//*[@id=\"root\"]/div[2]/div/div[4]/div/div[2]/div[3]/button[2]"));
		 * Thread.sleep(5000); driver.findElement(By.xpath(
		 * "//*[@id='root']/div/div/div[4]/div/div/div[3]/button[2]")).click();
		 * js.executeScript("arguments[0].scrollIntoView(true);", button);
		 */

	}
}
