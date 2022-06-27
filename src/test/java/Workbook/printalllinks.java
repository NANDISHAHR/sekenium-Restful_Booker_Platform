package Workbook;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import common.BaseTest;

public class printalllinks extends BaseTest {

	@Test
	public void printalllinksInapplication() throws Exception {

		Set<String> brokenlinkURLs = new HashSet<String>();
		//driver.get("https://automationintesting.online/#/");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("The total number of Tags are " + links.size());

		for (int i = 0; i < links.size(); i++) {

			System.out.println("Links on the page are " + links.get(i).getAttribute("href"));
			System.out.println("The text on the page is " + links.get(i).getText());

		}
		
		for (WebElement link : links) {
			String linkUrl = link.getAttribute("href");
			String UrlTitle = link.getText();
			System.out.println(UrlTitle);
			URL url = new URL(linkUrl);
			URLConnection urlconnection = url.openConnection();
			HttpURLConnection httpurlconnection = (HttpURLConnection) urlconnection;
			httpurlconnection.setConnectTimeout(5000);
			httpurlconnection.connect();

			/*
			 * if (httpurlconnection.getResponseCode() == 200) { System.out.println(linkUrl
			 * + " - " + httpurlconnection.getResponseMessage()); } else {
			 * System.err.println(linkUrl + " - " + httpurlconnection.getResponseCode() +
			 * " - " + httpurlconnection.getResponseMessage());
			 * httpurlconnection.disconnect(); }
			 */
			if (httpurlconnection.getResponseCode() != 200) {
				brokenlinkURLs.add(linkUrl);
			}
			httpurlconnection.disconnect();
				
		}
	for (String brokenLinkurl : brokenlinkURLs) 
		System.err.println(brokenLinkurl);
	}
}