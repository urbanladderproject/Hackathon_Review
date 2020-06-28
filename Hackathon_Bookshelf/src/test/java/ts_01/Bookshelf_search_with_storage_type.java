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

public class Bookshelf_search_with_storage_type extends utils.ExtentReport {

	public static WebDriver driver;

	public static String browsertype;
	
	Excelutils excel = new Excelutils();

	@BeforeClass(alwaysRun = true)
	@Parameters({ "Browser", "Environment" })
	public void setUp(@Optional("chrome") String browser, @Optional("local") String environment) {
		driver = Environment_Setup.getDriver(browser, environment);
		browsertype = browser;
	}
	
	
	//search for bookshelf by mentioning the storage type in the search bar
	@Test
	public void search_with_Storagetype() throws Exception{
		
		Report report = new Report();
		
		ExtentReport.log = ExtentReport.reports.createTest("Test Open Bookshelf");
		
		report.startBrowser(log);
		
		log.pass(MarkupHelper.createLabel("Browser started successfully", ExtentColor.GREEN));
		
		Url_navigating_homepage homepage = PageFactory.initElements(driver, Url_navigating_homepage.class);
		
		report.select(log, "Search for Open Bookshelf");
		
		log.pass(MarkupHelper.createLabel("Open Bookshelf is searched successfully", ExtentColor.GREEN));
		
		homepage.clearpage();
		
		homepage.searchText("Search with storage");

		homepage.searchbutton();
		
		Bookshelf bookshelf = PageFactory.initElements(driver, Bookshelf.class);
		
		String searched_data = bookshelf.get_searched_title();
		
		Assert.assertTrue(searched_data.contains("Open Bookshelf"));
		
		bookshelf.stockdetails();
				
		bookshelf.getresult("Price",browsertype);
		
		Screenshots.takesnap(driver, "Search with given storage type",browsertype);
		
		report.display(log, "Bookshelf storage type was selected");
		
		log.pass(MarkupHelper.createLabel("Bookshelf with storage type was selected successfully", ExtentColor.GREEN));
		
		report.closeBrowser(log);
		
		log.pass(MarkupHelper.createLabel("Browser closed successfully", ExtentColor.GREEN));

	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		// System.out.println("after method");
		if (result.isSuccess())
			excel.reportToExcel("Bookshelf search with storage type Test: SUCCESS",browsertype);
		else
			excel.reportToExcel("Bookshelf search with storage type Test: FAILURE",browsertype);
	}

	@AfterClass
	public void closeBrowser() throws IOException {
		excel.reportToExcel("Bookshelf search with storage type Test: ENDED",browsertype);
		// close the driver

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.quit();
	}
	
}
