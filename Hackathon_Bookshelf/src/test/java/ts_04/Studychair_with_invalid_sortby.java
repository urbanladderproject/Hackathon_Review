package ts_04;

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

import pageobjects.StudyChair;
import pageobjects.Url_navigating_homepage;
import setup.Environment_Setup;
import utils.Excelutils;
import utils.Report;

public class Studychair_with_invalid_sortby extends utils.ExtentReport {

	public static WebDriver driver;

	public static String browsertype;
	
	Excelutils excel = new Excelutils();

	
	
	@BeforeClass(alwaysRun = true)
	@Parameters({ "Browser", "Environment" })
	public void setUp(@Optional("chrome") String browser, @Optional("local") String environment) {
		driver = Environment_Setup.getDriver(browser, environment);
		browsertype = browser;
	}
	
	
	//search for study chair with other sort by options
	@Test
	public void invalid_sortby() throws Exception{
		
		Report report = new Report();
		
		log = reports.createTest("Study chair with invalid sort by options");
		
		report.startBrowser(log);
		
		log.pass(MarkupHelper.createLabel("Browser started successfully", ExtentColor.GREEN));
		
		Url_navigating_homepage homepage = PageFactory.initElements(driver, Url_navigating_homepage.class);
		
		report.select(log, "Study chair with invalid sort by");
		
		log.pass(MarkupHelper.createLabel("Study chair with invalid sort by wasn't searched", ExtentColor.GREEN));
		
		homepage.clearpage();
		
		homepage.searchText("Search for study chair");

		homepage.searchbutton();
		
		StudyChair sc = PageFactory.initElements(driver, StudyChair.class);

		sc.getresult(browsertype, "Invalid Sort By");
		
		report.display(log, "Study chair with invalid sort by wasn't searched");
		
		log.pass(MarkupHelper.createLabel("Study chair with invalid sort by wasn't searched", ExtentColor.GREEN));
		
		report.closeBrowser(log);
		
		log.pass(MarkupHelper.createLabel("Browser closed successfully", ExtentColor.GREEN));

	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		// System.out.println("after method");
		if (result.isSuccess())
			excel.reportToExcel("Study chair with invalid sort by Test: SUCCESS",browsertype);
		else
			excel.reportToExcel("Study chair with invalid sort by Test: FAILURE",browsertype);
	}

	@AfterClass
	public void closeBrowser() throws IOException {
		excel.reportToExcel("Study chair with invalid sort by Test: ENDED",browsertype);
		// close the driver

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.quit();
	}



}
