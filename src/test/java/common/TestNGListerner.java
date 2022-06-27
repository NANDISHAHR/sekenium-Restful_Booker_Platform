package common;

import org.openqa.selenium.devtools.v85.page.Page.CaptureScreenshotFormat;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListerner extends BaseTest implements ITestListener {

	public void onTestStart(ITestResult result) {
		System.out.println("onTestStart");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("onTestSuccess");
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("onTestFailure");
		CapturingScreenshotwhenfails(result.getTestContext().getName()+ "_" +result.getMethod().getMethodName()+".jpg");
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("onTestSkipped");
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		System.out.println("onTestFailedWithTimeout");
	}

	public void onStart(ITestContext context) {
		System.out.println("onStart");
	}

	public void onFinish(ITestContext context) {
		System.out.println("onFinish");
	}

}
