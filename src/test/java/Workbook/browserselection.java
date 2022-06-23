package Workbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class browserselection {
public static void main(String[] args) throws IOException {
		
		Properties properties = new Properties();
		FileInputStream inputstream =  new FileInputStream("C:\\Users\\nandish.h.r\\eclipse-workspace1\\Restful_Booker_Platform\\src\\test\\resources\\properties\\testdata.properties");
		properties.load(inputstream);
		String browser = properties.getProperty("browser");
		System.out.println(browser);
		String URL = properties.getProperty("url");
		System.out.println(URL);

}
}
