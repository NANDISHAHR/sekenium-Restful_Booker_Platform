package navigatetosite;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import common.BaseTest;

public class BookingHotel extends BaseTest {

	String month = "June 2023";
	String day = "10";

	@Test(priority = 1)
	public void scrollIntoform() {

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		WebElement form_page = driver.findElement(By.xpath("//*[@id='root']/div[2]/div/div[4]/div/div/div[3]/button"));
		js.executeScript("arguments[0].scrollIntoView(true);", form_page);

	}

	@Test(priority = 2)
	public void Click_book_room_link() {
		driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div/div[3]/button")).click();
		Assert.assertTrue(
				driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[2]/div")).isDisplayed(),
				"The hotel booking form page is not opened");
	}

	@Test(priority = 3)
	public void closing_form() {
		WebElement Close_button = driver
				.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[3]/button[1]"));
		// assertion close button
		String actualclosebuttoncolor = driver
				.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[3]/button[1]"))
				.getCssValue("border-color");
		String Expectedclosebuttoncolor = "rgb(220, 53, 69)";
		assertEquals(actualclosebuttoncolor, Expectedclosebuttoncolor, "Close Button color is not proper");
		Close_button.click();

		String tagname = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[4]/div/div/div[3]/button"))
				.getAttribute("type");
		System.out.println(tagname);

		// Aeertion for book this hotel button
		String actualbookthisHotelbuttoncolor = driver
				.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[4]/div/div/div[3]/button"))
				.getCssValue("border-color");
		String ExpectedbookthisHotelbuttoncolor = "rgb(0, 123, 255)";
		assertEquals(actualbookthisHotelbuttoncolor, ExpectedbookthisHotelbuttoncolor,
				"Book This Hotel Button color is not proper");
	}

	@Test(priority = 4)
	public void MonthAndYear() {

		driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div/div[3]/button")).click();
		while (true) {

			String month_Year = driver
					.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[2]/div/div[1]/span[2]"))
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
	}

	@Test(priority = 5)
	public void SelectDatesAndBookHotel() throws Exception {

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

		/*
		 * JavascriptExecutor js = ((JavascriptExecutor) driver); WebElement
		 * BookbuttonLink = driver.findElement(By.xpath(
		 * "//*[@id=\"root\"]/div[2]/div/div[4]/div/div[2]/div[3]/button[2]"));
		 * js.executeScript("arguments[0].scrollIntoView(true);", BookbuttonLink);
		 */

		WebElement Book_button = driver
				.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[3]/button[2]"));
		Book_button.click();

		String ActualErrorMessage = driver
				.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[4]/div/div[2]/div[3]/div[5]")).getText();
		System.out.println(ActualErrorMessage);
		String ExpectedErrorMessage = "size must be between 3 and 30\r\n" + "Firstname should not be blank\r\n"
				+ "Lastname should not be blank\r\n" + "size must be between 3 and 18\r\n" + "must not be empty\r\n"
				+ "size must be between 11 and 21\r\n" + "must not be empty" + " ";

		softAssert.assertNotEquals(ActualErrorMessage, ExpectedErrorMessage, "The error mesage is not matched");

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		WebElement BookbuttonLink = driver
				.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[4]/div/div[2]/div[3]/button[2]"));
		js.executeScript("arguments[0].scrollIntoView(true);", BookbuttonLink);

		driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[3]/div[1]/input"))
				.sendKeys("TESTSAMPLE123");
		driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[3]/div[2]/input"))
				.sendKeys("TEST671");
		driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[3]/div[3]/input"))
				.sendKeys("abcdef@gmail.com");
		driver.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[3]/div[4]/input"))
				.sendKeys("01531231231");

		// button assertion
		String actualbuttoncolor = driver
				.findElement(By.xpath("//*[@id='root']/div/div/div[4]/div/div[2]/div[3]/button[2]"))
				.getCssValue("border-color");
		String Expectedbuttoncolor = "rgb(0, 123, 255)";
		assertEquals(actualbuttoncolor, Expectedbuttoncolor, "Button color is not proper");

		Book_button.click();
		
		WebElement firstnameinputfield = driver
				.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[4]/div/div[2]/div[3]/div[1]/input"));
		js.executeScript("arguments[0].scrollIntoView(true);", firstnameinputfield);

		if (driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/div[2]/h3")).isDisplayed() == true) {

			Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/div[2]/h3")).isDisplayed(),
					"Booking Successful! message is not displyed");
			System.out.println("Booking Successful! message is displyed");
		} else {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@id=\"root\"]/div[2]/div/div[4]/div/div[2]/div[3]/div[5]")));
		}
	}

	@Test(priority = 6)
	public void Bookingsuccess() {

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
