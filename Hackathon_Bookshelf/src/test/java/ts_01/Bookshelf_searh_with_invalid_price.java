package ts_01;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import pageobjects.Bookshelf;
import pageobjects.Url_navigating_homepage;
import setup.Environment_Setup;
import utils.Excelutils;
import utils.ExtentReport;
import utils.Report;
import utils.Screenshots;

public class Bookshelf_searh_with_invalid_price extends utils.ExtentReport{
	
	public static WebDriver driver;

	public static String browsertype;
	
	Excelutils excel = new Excelutils();

	
	
	@BeforeClass(alwaysRun = true)
	@Parameters({ "Browser", "Environment" })
	public void setUp(@Optional("chrome") String browser, @Optional("local") String environment) {
		driver = Environment_Setup.getDriver(browser, environment);
		browsertype = browser;
	}
	
	
	//search for bookshelf with an invalid given price
	@Test
	public void search_for_invalid_price() throws Exception{
		
		Report report = new Report();
		
		ExtentReport.log = ExtentReport.reports.createTest("Test Bookshelf of invalid price");
		
		report.startBrowser(log);
		
		log.pass(MarkupHelper.createLabel("Browser started successfully", ExtentColor.GREEN));
		
		Url_navigating_homepage homepage = PageFactory.initElements(driver, Url_navigating_homepage.class);
		
		report.select(log, "Search for Bookshelf of invalid price");
		
		log.pass(MarkupHelper.createLabel("Bookshelf with invalid price wasn't searched", ExtentColor.GREEN));
		
		System.out.println("Search Data");
		
		homepage.searchText("Search Data");

		homepage.searchbutton();
		
		Bookshelf bookshelf = PageFactory.initElements(driver, Bookshelf.class);
		
		String searched_data = bookshelf.get_searched_title();
		
		Assert.assertTrue(searched_data.contains("Bookshelf"));
		
		bookshelf.stockdetails();
		
		bookshelf.storage_dropdown();
		
		bookshelf.select_storagetype("Search with Storage");
		
		bookshelf.getresult("Invalid Price",browsertype);
		
		Screenshots.takesnap(driver, "Invalid Price",browsertype);
		
		report.display(log, "Bookshelf with invalid price wasn't listed");
		
		log.pass(MarkupHelper.createLabel("Bookshelf with invalid price amount wasn't listed", ExtentColor.GREEN));
		
		report.closeBrowser(log);
		
		log.pass(MarkupHelper.createLabel("Browser closed successfully", ExtentColor.GREEN));

	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		// System.out.println("after method");
		if (result.isSuccess())
			excel.reportToExcel("Bookshelf invalid price Test: SUCCESS",browsertype);
		else
			excel.reportToExcel("Bookshelf invalid price Test: FAILURE",browsertype);
	}

	@AfterClass
	public void closeBrowser() throws IOException {
		excel.reportToExcel("Bookshelf invalid price Test: ENDED",browsertype);

		// close the driver
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.quit();
	}

}
