package ts_03;

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

import pageobjects.GiftCard;
import setup.Environment_Setup;
import utils.Excelutils;
import utils.Report;
import utils.Screenshots;

public class Giftcard_with_invalid_order_type extends utils.ExtentReport {

	public static WebDriver driver;

	public static String browsertype;

	Excelutils excel = new Excelutils();

	@BeforeClass(alwaysRun = true)
	@Parameters({ "Browser", "Environment" })
	public void setUp(@Optional("chrome") String browser, @Optional("local") String environment) {
		driver = Environment_Setup.getDriver(browser, environment);
		browsertype = browser;
	}

	
	//Tries to access the other order types near to 'Gift card'
	@Test
	public void invalid_order_type() throws Exception {
		
		Report report = new Report();

		log = reports.createTest("Test Invalid order type");

		report.startBrowser(log);

		log.pass(MarkupHelper.createLabel("Browser started successfully", ExtentColor.GREEN));

		GiftCard gc  = PageFactory.initElements(driver, GiftCard.class);

		report.select(log, "Selecting an invalid order type");

		log.pass(MarkupHelper.createLabel("An invalid order type wan't selected", ExtentColor.GREEN));

		gc.enter_to_giftcard("Invalid Order type");
		
		Screenshots.takesnap(driver, "invalid order type",browsertype);

		report.display(log, "Testing to select an invalid order type");

		log.pass(MarkupHelper.createLabel("Invalid order type not selected", ExtentColor.GREEN));

		report.closeBrowser(log);

		log.pass(MarkupHelper.createLabel("Browser closed successfully", ExtentColor.GREEN));


	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		// System.out.println("after method");
		if (result.isSuccess())
			excel.reportToExcel("Select an invalid order type Test: SUCCESS",browsertype);
		else
			excel.reportToExcel("Select an invalid order type Test: FAILURE",browsertype);
	}

	@AfterClass
	public void closeBrowser() throws IOException {
		excel.reportToExcel("Select an invalid order type Test: ENDED",browsertype);
		// close the driver
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.quit();
	}

}
