package common;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class readbrowserfromexcel {

	public static void main(String[] args) throws Exception {

		WebDriver driver = null;
		File file = new File(
				"C:\\Users\\nandish.h.r\\eclipse-workspace1\\Restful_Booker_Platform\\src\\test\\resources\\ExcelFiles\\Test3.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(2);
		
		int rowcount = sheet.getPhysicalNumberOfRows();

		for (int i = 0; i < rowcount; i++) {
			XSSFRow row = sheet.getRow(i);

			int cellcount = row.getPhysicalNumberOfCells();
			for (int j = 0; j < cellcount; j++) {
				XSSFCell cell = row.getCell(j);
				String cellValue = getcellvalue(cell);
				System.out.print(cellValue);
				String browser = cellValue;
				
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
			System.out.println();
			driver.close();	
		}
		workbook.close();
		fis.close();

	}

	public static String getcellvalue(XSSFCell cell) {
		switch (cell.getCellType()) {
		case NUMERIC:
			return String.valueOf(cell.getNumericCellValue());
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case STRING:
			return cell.getStringCellValue();
		default:
			return cell.getStringCellValue();

		}
	}

}
