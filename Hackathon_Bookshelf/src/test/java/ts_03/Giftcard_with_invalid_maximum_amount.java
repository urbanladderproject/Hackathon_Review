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

public class Giftcard_with_invalid_maximum_amount  extends utils.ExtentReport {

	public static WebDriver driver;

	public static String browsertype;

	Excelutils excel = new Excelutils();

	@BeforeClass(alwaysRun = true)
	@Parameters({ "Browser", "Environment" })
	public void setUp(@Optional("chrome") String browser, @Optional("local") String environment) {
		driver = Environment_Setup.getDriver(browser, environment);
		browsertype = browser;
	}

	//Tries to fill a value greater than 5,00,000 in the gift amount field
	@Test
	public void invalid_maximum_amount() throws Exception {
		
		Report report = new Report();

		log = reports.createTest("Test Invalid maximum Gift Card amount");

		report.startBrowser(log);

		log.pass(MarkupHelper.createLabel("Browser started successfully", ExtentColor.GREEN));

		GiftCard gc  = PageFactory.initElements(driver, GiftCard.class);

		report.select(log, "Invalid maximum Gift Card amount");

		log.pass(MarkupHelper.createLabel("Invalid maximum giftcard amount wasn't accepted", ExtentColor.GREEN));

		gc.enter_to_giftcard("Order type");

		gc.select_gifttype("Occasion");

		gc.enter_amount("Invalid maximum Gift Card amount");
		
		Screenshots.takesnap(driver, "Invalid maximum gift card amount",browsertype);
		
		report.display(log, "Invalid maximum Gift Card amount didn't perform further operations");

		log.pass(MarkupHelper.createLabel("Invalid maximum Gift Card amount wasn't accepted", ExtentColor.GREEN));

		report.closeBrowser(log);

		log.pass(MarkupHelper.createLabel("Browser closed successfully", ExtentColor.GREEN));


	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		// System.out.println("after method");
		if (result.isSuccess())
			excel.reportToExcel("Invalid maximum Gift Card amount Test: SUCCESS",browsertype);
		else
			excel.reportToExcel("Invalid maximum Gift Card amount Test: FAILURE",browsertype);
	}

	@AfterClass
	public void closeBrowser() throws IOException {
		excel.reportToExcel("Invalid maximum Gift Card amount Test: ENDED",browsertype);
		// close the driver
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.quit();
	}		


}
