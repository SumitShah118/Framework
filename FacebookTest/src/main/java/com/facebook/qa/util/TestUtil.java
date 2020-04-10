package com.facebook.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.facebook.qa.base.TestBase;

public class TestUtil extends TestBase {
	
	public static long PAGELOAD_TIMEOUT = 40;
	public static long IMPLICIT_TIMEOUT = 30;
	public static long THREAD_SLEEP = 5000;
	public static String TESTDATA_SHEET_PATH = "S:\\JavaforSelenium\\FacebookTest\\src\\main\\java\\com\\facebook\\qa\\testdata\\FacebookDataForLogin.xlsx";
	
	public static Workbook book;
	public static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}
		
	public static void extentReportInitialization() {	
		reporter.config().setDocumentTitle("FACEBOOK AUTOMATION TEST");
		reporter.config().setReportName("FACEBOOK HTML REPORT FOR MANAGEMENT");
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setTimeStampFormat("DD/MM/YYYY");
		reporter.config().setLevel(Status.INFO);
		
		extent.attachReporter(reporter);
		extent.setSystemInfo("Broswer","Chrome by GOOGLE");
		extent.setSystemInfo("Auotmation Tester","Sumit Shah");
		extent.setSystemInfo("Automation Env","SIT");
	}
	
	public static String getScreenShot() throws IOException {
		
		String date=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String destPath = System.getProperty("user.dir") + "/test-output/myfile_"+date+".jpeg";
	
		TakesScreenshot screen=(TakesScreenshot)driver;		
		File src=screen.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(destPath));
		return destPath;
	}
	
	public static String getCurrentDateTime() {
		
		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currrentDate = new Date();
		String dateNew = customFormat.format(currrentDate);
		return dateNew;
	}
}
