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

public class Giftcard_with_valid_data extends utils.ExtentReport {

	public static WebDriver driver;

	public static String browsertype;

	Excelutils excel = new Excelutils();

	@BeforeClass(alwaysRun = true)
	@Parameters({ "Browser", "Environment" })
	public void setUp(@Optional("chrome") String browser, @Optional("local") String environment) {
		driver = Environment_Setup.getDriver(browser, environment);
		browsertype = browser;
	}

	//Enters the correct values in all the fields and produces the result
	@Test
	public void valid_giftcard() throws Exception {
		
		Report report = new Report();

		log = reports.createTest("Test Open Bookshelf");

		report.startBrowser(log);

		log.pass(MarkupHelper.createLabel("Browser started successfully", ExtentColor.GREEN));

		GiftCard gc  = PageFactory.initElements(driver, GiftCard.class);

		report.select(log, "Filling a complete Giftcard form");

		log.pass(MarkupHelper.createLabel("Filled a complete Giftcard form successfully", ExtentColor.GREEN));

		gc.enter_to_giftcard("Order type");

		gc.select_gifttype("Occasion");

		gc.enter_amount("Gift Card amount");

		gc.next_page();

		gc.recepient_name("Recepient Name");

		gc.recepient_email("Recepient Email");

		gc.sender_name("Sender Name");

		gc.sender_email("Sender Email");

		gc.sender_phone("Sender Phonenumber");

		gc.confirm();

		report.display(log, "Gift Card form was filled successfully");

		log.pass(MarkupHelper.createLabel("Gift Card form was filled successfully", ExtentColor.GREEN));

		report.closeBrowser(log);

		log.pass(MarkupHelper.createLabel("Browser closed successfully", ExtentColor.GREEN));


	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		// System.out.println("after method");
		if (result.isSuccess())
			excel.reportToExcel("Gift Card valid form filling Test: SUCCESS",browsertype);
		else
			excel.reportToExcel("Gift Card valid form filling Test: FAILURE",browsertype);
	}

	@AfterClass
	public void closeBrowser() throws IOException {
		excel.reportToExcel("Gift Card valid form filling Test: ENDED",browsertype);
		// close the driver

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.quit();
	}
}
