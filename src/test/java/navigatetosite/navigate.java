package navigatetosite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import common.BaseTest;

//@Listeners(TestNGListerner.class)
public class navigate extends BaseTest{

	@Test
	public void navigatetoapplication() {
		

		SoftAssert softassert = new SoftAssert();
		String ExpectedURL = "https://automationintesting.online/#/";
		String ExpectedTitle = "Restful-booker-platform demo";
		
		String currnetURL = driver.getCurrentUrl();
		System.out.println("The current page URL is: "+currnetURL);
		softassert.assertEquals(currnetURL, ExpectedURL , "The Current URL is incorrect");
		
		String Title = driver.getTitle();
		System.out.println("The title of the page is: "+Title);
		softassert.assertEquals(Title, ExpectedTitle, "The tile verfication is failed");
		
		//String PageSource =  driver.getPageSource();
		//System.out.print("The page source are "+PageSource);	
		softassert.assertAll();
	}

}
