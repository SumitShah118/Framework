package in.amazon.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import in.amazon.qa.base.TestBase;

public class TestUtil extends TestBase{

	public static int PAGE_LOAD_TIMEOUT = 30;
	public static int IMPLICIT_WAIT = 30;
	public static int THREAD_SLEEP_TIMEOUT = 60;
	public static String SEARCH_BAR_TEXT = "Redmi Note 8 6gb 128gb";
	public static String IMEI_NUMBER = "351893087033059";
	
	//BILLING ADDRESS
	public static String BILLING_FULL_NAME = "SUMIT N SHAH";
	public static String MOBILE_NUMBER = "9426883001";
	public static String POSTAL_CODE = "387001";
	public static String ADDRESS_LINE_1 = "10, VRAJBHUMIC SOC., OPP.KRISHNA TOWNSHIP,VANIYAVAD, COLLAGE ROAD";
	public static String CITY = "NADIAD";
	public static String STATE = "GUJARAT";
	
	static String filePath = System.getProperty("user.dir") + "/src/logo" + System.currentTimeMillis() + ".jpg";
	static File destFile=new File(filePath);
	public static void takeScreenshot(WebDriver ldriver) {
		TakesScreenshot src=(TakesScreenshot)ldriver;
		File srcFile=src.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, destFile);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e2) {
			e2.printStackTrace();
		}
	}
	
	static String filePathAndName = System.getProperty("user.dir") + "/src/main/java/in/amazon/qa/testdata/TestDataForProject.xlsx" ;
	public static Object[][] readDataFromExcel(String sheetName) throws IOException {
		File file = new File(filePathAndName);
		FileInputStream fis=new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet=wb.getSheet(sheetName);
		int lastRow = sheet.getLastRowNum();
		System.out.println("ROW COUNT "+lastRow);
		int lastcol = sheet.getRow(0).getLastCellNum();
		System.out.println("COLUMN COUNT "+lastcol);
		Object cellValue[][]=new Object[lastRow][lastcol];
		for(int i=1;i<=lastRow;i++) {
			for(int j=0;j<lastcol;j++) {
				cellValue[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();
				System.out.println(cellValue[i][j]);
			}
		}
		return cellValue;
	}
}
