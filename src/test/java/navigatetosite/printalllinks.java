package navigatetosite;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import common.BaseTest;

public class printalllinks extends BaseTest {

	@Test
	public void printalllinksInapplication() throws Exception {

		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("The total number of Tags are " + links.size());
		
		for (WebElement link : links) {
			String linkUrl = link.getAttribute("href");
			
			String UrlTitle = link.getText();
			System.out.println(UrlTitle);
			URL url = new URL(linkUrl);
			URLConnection urlconnection = url.openConnection();
			HttpURLConnection httpurlconnection = (HttpURLConnection) urlconnection;
			httpurlconnection.setConnectTimeout(5000);
			httpurlconnection.connect();

			if (httpurlconnection.getResponseCode() == 200) {
				System.out.println(linkUrl + " - " + httpurlconnection.getResponseMessage());
			}
			else {
				System.err.println(linkUrl + " - " + httpurlconnection.getResponseCode() + " - "
						+ httpurlconnection.getResponseMessage());
				httpurlconnection.disconnect();
			}
			
		}

	}

}