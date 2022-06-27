package common;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
	public static String ScreenshotSubfolderName;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;

	protected SoftAssert softAssert = new SoftAssert();

	@BeforeSuite
	public void initializeExtendReports() {
		ExtentSparkReporter sparkReporter_All = new ExtentSparkReporter("AllTests.HTML");
		sparkReporter_All.config().setReportName("All Tests Report");
		extentReports = new ExtentReports();

		ExtentSparkReporter sparkReporter_failed = new ExtentSparkReporter("FailedTests.HTML");
		sparkReporter_failed.filter().statusFilter().as(new Status[] { Status.FAIL });
		sparkReporter_failed.config().setReportName("Failure Report");

		extentReports.attachReporter(sparkReporter_All, sparkReporter_failed);

		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.Version"));
		extentReports.setSystemInfo("App Url", "https://automationintesting.online/#/");
	}

	@Parameters("browsername")
	@BeforeTest
	public void InitialiseBrowser(ITestContext context, @Optional("Edge") String browsername) {

		switch (browsername.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Browser name is inavlid");
			break;
		}
		driver.manage().window().maximize();
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		String device = capabilities.getBrowserName() + " " + capabilities.getBrowserVersion();
		String author = context.getCurrentXmlTest().getParameter("author");

		driver.get("https://automationintesting.online/#/");
		// extentTest.info("Navigated to URL");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		extentTest = extentReports.createTest(context.getName());
		// extentTest.assignAuthor(author);
		System.out.println(author);
		extentTest.assignDevice(device);

	}

	@AfterMethod()
	public void CheckStatus(Method m, ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {

			String screenshotPath = null;
			CapturingScreenshotwhenfails(
					result.getTestContext().getName() + "_" + result.getMethod().getMethodName() + ".jpg");
			extentTest.addScreenCaptureFromPath(screenshotPath);
			extentTest.fail(result.getThrowable());

		} else if (result.getStatus() == ITestResult.SUCCESS) {

			extentTest.pass(m.getName() + " is Passesd");
		}
		extentTest.assignCategory(m.getAnnotation(Test.class).groups());
	}

	@AfterTest
	public void teardown() {

		driver.quit();
		softAssert.assertAll();
	}

	@AfterSuite
	public void generateExtentReport() throws Exception {
		extentReports.flush();
		Desktop.getDesktop().browse(new File("AllTests.HTML").toURI());
		Desktop.getDesktop().browse(new File("FailedTests.HTML").toURI());
	}

	public void CapturingScreenshotwhenfails(String fileName) {

		if (ScreenshotSubfolderName == null) {

			LocalDateTime myDateObj = LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
			ScreenshotSubfolderName = myDateObj.format(myFormatObj);
		}

		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File sourcefile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		File destfile = new File("./Screenshots2/" + ScreenshotSubfolderName + "/" + fileName);

		try {
			FileUtils.copyFile(sourcefile, destfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Screenshot saved successfully");
	}

}
