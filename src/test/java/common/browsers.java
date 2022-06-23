package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import Workbook.browserselection;
import io.github.bonigarcia.wdm.WebDriverManager;


public class browsers extends browserselection{
	
	public static String browser = "msEdge";// External configuration - XLS,CSV
	//public static EdgeDriver driver; //global declaration
	//public static ChromeDriver driver1;
	public static WebDriver driver;

	@BeforeSuite
	public void lunchbrowser1() {

		if (browser.equals("msEdge")) {
			WebDriverManager.edgedriver().setup();
		    driver = new EdgeDriver();
		} else if (browser.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			 driver = new FirefoxDriver();
		}

	}
	
	@AfterSuite
	public void closingbroswer() {
		driver.close();
	}
}
