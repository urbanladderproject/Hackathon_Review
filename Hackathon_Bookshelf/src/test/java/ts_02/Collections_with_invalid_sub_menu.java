package ts_02;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import pageobjects.Display_Collections;
import pageobjects.Url_navigating_homepage;
import setup.Environment_Setup;
import utils.Excelutils;
import utils.ExtentReport;
import utils.Report;

public class Collections_with_invalid_sub_menu extends utils.ExtentReport {

	public static WebDriver driver;

	public static String browsertype;
	
	Excelutils excel = new Excelutils();

	@BeforeClass(alwaysRun = true)
	@Parameters({ "Browser", "Environment" })
	public void setUp(@Optional("chrome") String browser, @Optional("local") String environment) {
		driver = Environment_Setup.getDriver(browser, environment);
		browsertype = browser;
	}
	
	
	//rejects to display a different sub menu other than 'Being at Home'
	@Test
	public void valid_main_menu_and_invalid_submenu () throws Exception{
		
		Report report = new Report();
		
		ExtentReport.log = ExtentReport.reports.createTest("Test for valid main menu and invalid submenu");
		
		report.startBrowser(log);
		
		log.pass(MarkupHelper.createLabel("Browser started successfully", ExtentColor.GREEN));
		
		Url_navigating_homepage homepage = PageFactory.initElements(driver, Url_navigating_homepage.class);

		report.select(log, "Search for Bookshelf");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		homepage.clearpage();
		
		Display_Collections collections = PageFactory.initElements(driver, Display_Collections.class);
		
		report.select(log, "Different sub menu test");
		
		log.pass(MarkupHelper.createLabel("The different sub menu options are not displayed", ExtentColor.GREEN));
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		collections.collection_click("Menu to be selected");
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		collections.getdata("Invalid sub-menu to be selected");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		report.display(log, "A different main menu was tried to be selected and was failed");
		
		log.pass(MarkupHelper.createLabel("A different sub menu was tried to be selected and was failed successfully", ExtentColor.GREEN));
		
		report.closeBrowser(log);
		
		log.pass(MarkupHelper.createLabel("Browser closed successfully", ExtentColor.GREEN));

	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		// System.out.println("after method");
		if (result.isSuccess())
			excel.reportToExcel("Different sub menu Test: SUCCESS",browsertype);
		else
			excel.reportToExcel("Different sub menu Test: FAILURE",browsertype);
	}

	@AfterClass
	public void closeBrowser() throws IOException {
		excel.reportToExcel("Different sub menu Test: ENDED",browsertype);
		// close the driver

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.quit();
	} 

}
