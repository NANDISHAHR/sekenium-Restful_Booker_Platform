package Workbook;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import navigatetosite.printalllinks;

public class headingandsubheadingcontents extends printalllinks {

	@Test
	public void headingcontens() {
		String heading = driver.findElement(By.xpath("//*[@id='collapseBanner']/div/div[1]/div/h1")).getText();
		System.out.println(heading);

	}

	@Test
	public void subheadingcontents() {

		String subheading = driver.findElement(By.xpath("//*[@id=\"collapseBanner\"]/div/div[1]/div/h4")).getText();
		System.out.println(subheading);
	}

	@Test
	public void image() {

		SoftAssert softassert = new SoftAssert();

		// List all images on the page
		List<WebElement> allimages = driver.findElements(By.tagName("img"));
		System.out.println("The total number of images are " + allimages.size());
		for (int i = 0; i < allimages.size(); i++) {
			System.out.println("The image on the page are " + allimages.get(i).getAttribute("src"));
		}

		// print image URL on the console
		String Exploartionimage = driver.findElement(By.xpath("//*[@id=\"collapseBanner\"]/div/div[2]/div[1]/img"))
				.getAttribute("src");
		System.out.println(Exploartionimage);

		// Automation Section

		// print image URL on the console
		String automation = driver.findElement(By.xpath("//*[@id=\"collapseBanner\"]/div/div[2]/div[2]/img"))
				.getAttribute("src");
		System.out.println(automation);

		// Infrastructure Section

		// print image URL on the console
		String Infrastructure = driver.findElement(By.xpath("//*[@id=\"collapseBanner\"]/div/div[2]/div[3]/img"))
				.getAttribute("src");
		System.out.println(Infrastructure);

		// Get Started section

		// print image URL on the console
		String GetStarted = driver.findElement(By.xpath("//*[@id=\"collapseBanner\"]/div/div[2]/div[4]/img"))
				.getAttribute("src");
		System.out.println(GetStarted);

		softassert.assertAll();

	}
}
