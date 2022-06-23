package Workbook;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.jodah.failsafe.internal.util.Assert;

public class datepickerautomaton {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String month = "July 2022";
		String day = "01";
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://automationintesting.online/#/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		WebElement form_page = driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div/div[3]/h3"));
		js.executeScript("arguments[0].scrollIntoView(true);", form_page);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[4]/div/div/div[3]/button")).click();

		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[4]/div/div[2]/div[3]/button[1]")).click();
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[4]/div/div/div[3]/button")).click();

		//Error validation
		WebElement date_picker = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[4]/div/div[2]/div[2]/div"));
		
		if (date_picker.isEnabled()) {
			//without selecting dates if we tries to book hotel
			driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[3]/div[1]/input"))
					.sendKeys("TESTSAMPLE1");
			driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[3]/div[2]/input"))
					.sendKeys("TEST1");
			driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[3]/div[3]/input"))
					.sendKeys("abcd@gmail.com");
			driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[3]/div[4]/input"))
					.sendKeys("013231231231");
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[4]/div/div[2]/div[3]/button[2]")).click();
		}
		while (true) {

			String month_Year = driver
					.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[4]/div/div[2]/div[2]/div/div[1]/span[2]"))
					.getText();

			if (month_Year.equals(month)) {
				break;
			} else {
				driver.findElement(
						By.xpath("//*[@id=\"root\"]/div/div/div[4]/div/div[2]/div[2]/div/div[1]/span[1]/button[3]"))
						.click();
			}
		}

		List<WebElement> alldays = driver.findElements(
				By.xpath("//*[@id=\"root\"]/div/div/div[4]/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/button"));

		for (WebElement ele : alldays) {

			String date_text = ele.getText();
			System.out.println(date_text);

			if (date_text.equals(day)) {

				WebElement date_start = driver.findElement(By.xpath(
						"//*[@id=\"root\"]/div[2]/div/div[4]/div/div[2]/div[2]/div/div[2]/div[4]/div[1]/div[3]"));
				Actions action = new Actions(driver);
				action.click(ele).perform();
				action.clickAndHold().dragAndDropBy(date_start, 0, 30).release().perform();
				WebElement totaldays = driver.findElement(By.xpath(
						"//*[@id=\"root\"]/div[2]/div/div[4]/div/div[2]/div[2]/div/div[2]/div[4]/div[2]/div[2]/div[2]/div/div"));

				System.out.println("The totala days and total Amout is: " + totaldays.getText());

				// Thread.sleep(3000);
				// System.out.println(date_start);
				// Thread.sleep(3000);
				// WebElement date_end =
				// driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[4]/div/div[2]/div[2]/div/div[2]/div[4]/div[1]/div[5]"));
				// System.out.println(date_end);
				// Actions action = new Actions(driver);
				// action.clickAndHold(date_start).perform();
				// Thread.sleep(3000);
				// action.dragAndDrop(date_start, date_end).release().perform();
				break;
			}
		}
		driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[3]/div[1]/input"))
				.sendKeys("TESTSAMPLE1");
		driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[3]/div[2]/input")).sendKeys("TEST1");
		driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[3]/div[3]/input"))
				.sendKeys("abcd@gmail.com");
		driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[3]/div[4]/input"))
				.sendKeys("013231231231");
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[4]/div/div[2]/div[3]/button[2]")).click();
		
		//Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/div[2]/h3")).isDisplayed();

		String successmessage = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/div[2]/h3")).getText();
		System.out.println(successmessage);

		String bookingconfirmedmessage = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/div[2]/p[1]"))
				.getText();
		System.out.println(bookingconfirmedmessage);

		String booking_dates = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/div[2]/p[2]")).getText();
		System.out.println(booking_dates);

		driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/button")).click();

	}
}
