package com.crm.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.plaf.FileChooserUI;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {
	
	public TestUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 30;
	public static String TESTDATA_SHEET_PATH = "/FreeCRMTest/src/main/java/com/crm/qa/testdata/TestData.txt";
	static Workbook book;
	static Sheet sheet;

	
	
	public void switchtoFrame() {
		
		driver.switchTo().frame(prop.getProperty("frame"));
	}
	
	public Object[][] getTestData(String sheetName) throws InvalidFormatException, IOException {
		
		FileInputStream fis = new FileInputStream(TESTDATA_SHEET_PATH);
		book = WorkbookFactory.create(fis);
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		int lastRow = sheet.getLastRowNum();
		int lastColumn = sheet.getRow(0).getLastCellNum();
		System.out.println(lastRow+"   \n"+lastColumn);
		for (int i=0; i < lastRow; i++) {
			
			for (int j=0; j < lastColumn; j++) {
				
				data[i][j] = sheet.getRow(i+1).getCell(j).toString();
			}
		}
		
		return data;
	
	}
	
	public static void takeScreenShot() throws IOException {
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		
		FileUtils.copyFile(scrFile, new File(currentDir+"/ScreenShots" + System.currentTimeMillis()+".png")); 
	}

}
