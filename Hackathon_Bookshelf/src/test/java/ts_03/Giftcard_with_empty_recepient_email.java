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

public class Giftcard_with_empty_recepient_email  extends utils.ExtentReport {

	public static WebDriver driver;

	public static String browsertype;

	Excelutils excel = new Excelutils();

	@BeforeClass(alwaysRun = true)
	@Parameters({ "Browser", "Environment" })
	public void setUp(@Optional("chrome") String browser, @Optional("local") String environment) {
		driver = Environment_Setup.getDriver(browser, environment);
		browsertype = browser;
	}

	
	//Tries to fill an empty value to the receipent email field of the form
	@Test
	public void empty_email() throws Exception {
		
		Report report = new Report();

		log = reports.createTest("Test Invalid recepient email");

		report.startBrowser(log);

		log.pass(MarkupHelper.createLabel("Browser started successfully", ExtentColor.GREEN));
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		Url_navigating_homepage homepage = PageFactory.initElements(driver, Url_navigating_homepage.class);

		homepage.clearpage();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		GiftCard gc  = PageFactory.initElements(driver, GiftCard.class);

		report.select(log, "Invalid recepient email");

		log.pass(MarkupHelper.createLabel("Invalid recepient email wasn't accepted", ExtentColor.GREEN));

		gc.enter_to_giftcard("Order type");

		gc.select_gifttype("Occasion");

		gc.enter_amount("Gift Card amount");

		gc.next_page();

		gc.recepient_name("Recepient Name");
		
		gc.recepient_email("Empty Recepient Email");

		gc.sender_name("Sender Name");

		gc.sender_email("Sender Email");

		gc.sender_phone("Sender Phonenumber");

		gc.confirm();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		Screenshots.takesnap(driver,"empty receipent email",browsertype);
		
		report.display(log, "Empty recepient email wasn't accepted");

		log.pass(MarkupHelper.createLabel("Empty recepient email wasn't accepted", ExtentColor.GREEN));
		
		report.closeBrowser(log);

		log.pass(MarkupHelper.createLabel("Browser closed successfully", ExtentColor.GREEN));


	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		// System.out.println("after method");
		if (result.isSuccess())
			excel.reportToExcel("Invalid recepient email accepting Test: SUCCESS",browsertype);
		else
			excel.reportToExcel("Invalid recepient email accepting Test: FAILURE",browsertype);
	}

	@AfterClass
	public void closeBrowser() throws IOException {
		excel.reportToExcel("Invalid recepient email accepting Test: ENDED",browsertype);
		// close the driver

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.quit();
	}


}
