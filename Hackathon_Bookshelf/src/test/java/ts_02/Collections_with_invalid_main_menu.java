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

public class Collections_with_invalid_main_menu extends utils.ExtentReport {

	public static WebDriver driver;

	public static String browsertype;

	Excelutils excel = new Excelutils();

	@BeforeClass(alwaysRun = true)
	@Parameters({ "Browser", "Environment" })
	public void setUp(@Optional("chrome") String browser, @Optional("local") String environment) {
		driver = Environment_Setup.getDriver(browser, environment);
		browsertype = browser;
	}


	//Tries to select other main menu available other than 'Collections'
	@Test
	public void invalid_main_menu () throws Exception{

		Report report = new Report();

		ExtentReport.log = ExtentReport.reports.createTest("Test for an invalid main menu selection");

		report.startBrowser(log);

		log.pass(MarkupHelper.createLabel("Browser started successfully", ExtentColor.GREEN));

		Url_navigating_homepage homepage = PageFactory.initElements(driver, Url_navigating_homepage.class);

		report.select(log, "Search for Bookshelf");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		homepage.clearpage();

		Display_Collections collections = PageFactory.initElements(driver, Display_Collections.class);

		report.select(log, "Different main menu test");

		log.pass(MarkupHelper.createLabel("The different main menu options are not selected", ExtentColor.GREEN));

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		collections.collection_click("Invalid Menu to be selected");
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		report.display(log, "Tried to select a different main menu and was failed");

		log.pass(MarkupHelper.createLabel("Tried to select a different main menu and was failed successfully", ExtentColor.GREEN));

		report.closeBrowser(log);

		log.pass(MarkupHelper.createLabel("Browser closed successfully", ExtentColor.GREEN));

	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		// System.out.println("after method");
		if (result.isSuccess())
			excel.reportToExcel("Different main menu Test: SUCCESS",browsertype);
		else
			excel.reportToExcel("Different main menu Test: FAILURE",browsertype);
	}

	@AfterClass
	public void closeBrowser() throws IOException {
		excel.reportToExcel("Different main menu Test: ENDED",browsertype);
		// close the driver
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.quit();
	} 

}
