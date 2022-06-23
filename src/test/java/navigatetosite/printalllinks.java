package navigatetosite;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import common.BaseTest;

public class printalllinks extends BaseTest {

	@Test
	public void printalllinksInapplication() {

		// driver.get("https://automationintesting.online/#/");
		List<WebElement> alltags = driver.findElements(By.tagName("a"));
		System.out.println("The total number of Tags are " + alltags.size());

		for (int i = 0; i < alltags.size(); i++) {
			System.out.println("Links on the page are " + alltags.get(i).getAttribute("href"));
			System.out.println("The text on the page is " + alltags.get(i).getText());
		}
	}
}