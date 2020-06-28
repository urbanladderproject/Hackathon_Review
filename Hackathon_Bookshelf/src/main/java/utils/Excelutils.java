package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Excelutils {

	public static String[][] details = new String[12][9] ;
	public static String path = System.getProperty("user.dir") + "\\testdata.xlsx";
	public static String Chrome_result = System.getProperty("user.dir") + "\\testresult_chrome.xlsx";
	public static String Firefox_result = System.getProperty("user.dir") + "\\testresult_firefox.xlsx";
	
	public static String Chrome_report = System.getProperty("user.dir") + "\\testreport_chrome.xlsx";
	public static String Firefox_report = System.getProperty("user.dir") + "\\testreport_firefox.xlsx";

	
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static int logCounter = 0;
	public static XSSFWorkbook reportWorkbook;
	public static XSSFSheet reportSheet;

	public static int run_num = 0;
	public static Map<String, String> readExcelData(String sheetname) throws Exception
	{
		System.out.println("Entered into excel utils");

		FileInputStream file = new FileInputStream(path);
		System.out.println("Selected the file " + file);
		
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheet(sheetname);

		DataFormatter formatter = new DataFormatter(Locale.US);
		Map<String, String> hmap = new HashMap<String, String>();
		String key, value;
		int n = sheet.getLastRowNum();
		for (int i = 0; i <= n; i++) {
			Row row = sheet.getRow(i);
			key = formatter.formatCellValue(row.getCell(0));
			value = formatter.formatCellValue(row.getCell(1));
			hmap.put(key, value);
		}

		System.out.println("Returning to search Page");
		
		file.close();

		return hmap;		

	}

	public static void writeExcelData(String[][] results,String browser) throws Exception {
		
		String path1 = null;

		System.out.println("Into write excel Data");
		if(browser.equalsIgnoreCase("Chrome")) {
			path1 = Chrome_result;
		}
		else if(browser.equalsIgnoreCase("Firefox") || browser.equalsIgnoreCase("Mozilla")) {
			path1 = Firefox_result;
		}

		FileInputStream files = new FileInputStream(new File(path1));
		XSSFWorkbook wb = new XSSFWorkbook(files);
		XSSFSheet sheet = wb.getSheet("testresult");
		int rownum;
		
		if(run_num==0) {
			rownum = 0;
			run_num++;
		}
		
		else {
			rownum=sheet.getLastRowNum() + 1;
		}
		//System.out.println(rownum);
		XSSFRow row;
		XSSFCell cell;
		for(int i=0;i<3;i++) 
		{

			row = sheet.createRow(rownum);
			cell = row.createCell(0);
			cell.setCellValue(results[i][0]);
			cell = row.createCell(1);
			cell.setCellValue(results[i][1]);
			rownum++;

		}

		FileOutputStream fos = new FileOutputStream(path1);
		wb.write(fos);
		System.out.println("Writing to excel sheet");
		fos.close();
		wb.close();

	}
	
	public void reportToExcel(String message,String browser) throws IOException {
		String path1 = null;

		System.out.println("Into write excel Data");
		if(browser.equalsIgnoreCase("Chrome")) {
			path1 = Chrome_report;
		}
		else if(browser.equalsIgnoreCase("Firefox") || browser.equalsIgnoreCase("Mozilla")) {
			path1 = Firefox_report;
		}

		FileInputStream files = new FileInputStream(new File(path1));
		reportWorkbook = new XSSFWorkbook(files);

		if (logCounter == 0)
			reportSheet = reportWorkbook.createSheet("Logger Sheet");
		System.out.println(logCounter + ": " + message);
		this.pushToSheet(message, logCounter);
		logCounter++;
		this.writeToFile("ExecutionReport",reportWorkbook);
	}
	
	public void pushToSheet(String data, int rowNum) {
		Row row = reportSheet.createRow(rowNum);
		Cell cell = row.createCell(0);
		cell.setCellValue(data);
	}

	public void writeToFile(String fileName, XSSFWorkbook workbook) throws IOException {
		File file =    new File(System.getProperty("user.dir")+"\\test-output\\Output Files\\" + fileName + ".xlsx");
		FileOutputStream writeFile = new FileOutputStream(file);
		workbook.write(writeFile);
		writeFile.close();
	}

	public static void writeExcelCollectionsData(String[] info, String browser) throws Exception {

		String path2=null;
		System.out.println("Into write collection excel Data");
		if(browser.equalsIgnoreCase("Chrome")) {
			path2 = Chrome_result;
		}
		else if(browser.equalsIgnoreCase("Firefox") || browser.equalsIgnoreCase("Mozilla")) {
			path2 = Firefox_result;
		}

		FileInputStream files = new FileInputStream(new File(path2));
		XSSFWorkbook wb = new XSSFWorkbook(files);
		XSSFSheet sheet = wb.getSheet("testresult");
		int rownum = sheet.getLastRowNum() + 2;
		
		XSSFRow row;
		XSSFCell cell;
		for(int i=0;i<info.length;i++) {
			row = sheet.createRow(rownum);
			cell = row.createCell(0);
			cell.setCellValue(info[i]);
			rownum++;
		}

		FileOutputStream fos = new FileOutputStream(path2);
		wb.write(fos);
		System.out.println("Writing to excel sheet");
		fos.close();		
		wb.close();
	}
	
	public static void writeExcelStudyChairData(String[][] info, String browser) throws Exception {

		String path3=null;
		System.out.println("Into write excel Data");
		if(browser.equalsIgnoreCase("Chrome")) {
			path3 = Chrome_result;
		}
		else if(browser.equalsIgnoreCase("Firefox") || browser.equalsIgnoreCase("Mozilla")) {
			path3 = Firefox_result;
		}

		FileInputStream files = new FileInputStream(new File(path3));
		XSSFWorkbook wb = new XSSFWorkbook(files);
		XSSFSheet sheet = wb.getSheet("testresult");
		int rownum = sheet.getLastRowNum() + 2;
		
		XSSFRow row;
		XSSFCell cell;
		for(int i=0;i<info.length;i++) {
			row = sheet.createRow(rownum);
			cell = row.createCell(0);
			cell.setCellValue(info[i][0]);
			cell = row.createCell(1);
			cell.setCellValue(info[i][1]);
			rownum++;
		}

		FileOutputStream fos = new FileOutputStream(path3);
		wb.write(fos);
		System.out.println("Writing to excel sheet");
		fos.close();	
	}	
	
}