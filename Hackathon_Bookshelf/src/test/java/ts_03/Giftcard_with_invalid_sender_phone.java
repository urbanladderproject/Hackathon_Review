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
import pageobjects.Url_navigating_homepage;
import setup.Environment_Setup;
import utils.Excelutils;
import utils.Report;
import utils.Screenshots;

public class Giftcard_with_invalid_sender_phone extends utils.ExtentReport {

	public static WebDriver driver;

	public static String browsertype;

	Excelutils excel = new Excelutils();

	@BeforeClass(alwaysRun = true)
	@Parameters({ "Browser", "Environment" })
	public void setUp(@Optional("chrome") String browser, @Optional("local") String environment) {
		driver = Environment_Setup.getDriver(browser, environment);
		browsertype = browser;
	}

	//tries to fill a phone number less than 10 digits
	@Test
	public void invalid_sender_phone() throws Exception {
		
		Report report = new Report();

		log = reports.createTest("Test Invalid sender phone number");

		report.startBrowser(log);

		log.pass(MarkupHelper.createLabel("Browser started successfully", ExtentColor.GREEN));
		
		Url_navigating_homepage homepage = PageFactory.initElements(driver, Url_navigating_homepage.class);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		homepage.clearpage();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		GiftCard gc  = PageFactory.initElements(driver, GiftCard.class);

		report.select(log, "Invalid sender phone number");

		log.pass(MarkupHelper.createLabel("Invalid sender phone number wasn't accepted", ExtentColor.GREEN));

		gc.enter_to_giftcard("Order type");

		gc.select_gifttype("Occasion");

		gc.enter_amount("Gift Card amount");

		gc.next_page();

		gc.recepient_name("Recepient Name");
		
		gc.recepient_email("Recepient Email");

		gc.sender_name("Sender Name");

		gc.sender_email("Sender Email");

		gc.sender_phone("Invalid Sender Phonenumber");

		gc.confirm();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		Screenshots.takesnap(driver, "Invalid Sender Phone number",browsertype);

		report.display(log, "Invalid sender phone number wasn't accepted");

		log.pass(MarkupHelper.createLabel("Invalid sender phone number wasn't accepted", ExtentColor.GREEN));

		report.closeBrowser(log);

		log.pass(MarkupHelper.createLabel("Browser closed successfully", ExtentColor.GREEN));


	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		// System.out.println("after method");
		if (result.isSuccess())
			excel.reportToExcel("Invalid sender phone number accepting Test: SUCCESS",browsertype);
		else
			excel.reportToExcel("Invalid sender phone number accepting Test: FAILURE",browsertype);
	}

	@AfterClass
	public void closeBrowser() throws IOException {
		excel.reportToExcel("Invalid sender phone number accepting Test: ENDED",browsertype);
		// close the driver
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.quit();
	}


}
