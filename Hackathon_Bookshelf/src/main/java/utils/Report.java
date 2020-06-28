package utils;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Report {
	WebDriver driver;
	public ExtentReports reports;

	public Report() {
	}

	public void setReport(ExtentReports reports) {
		this.reports = reports;
	}

	public void fail(ExtentTest test) {
		test.log(Status.FAIL, "Test Failed");
	}

	public void startBrowser(ExtentTest test) {

//		test.log(Status.INFO, "Initializing Browser and getting HomePage of the Websited");
		test.log(Status.INFO, "Browser is initialized and navigated to the HomePage of the Website");
	}

	public void closeBrowser(ExtentTest test) {

		test.log(Status.INFO, "Browser is closed");
	}

	public void upcomingPage(ExtentTest test) {

		test.log(Status.INFO, "Navigating to upcoming bikes page");
	}

	public void HondaPage(ExtentTest test) {

//		test.log(Status.INFO, "Navigating to Honda bikes page");
		test.log(Status.INFO, "Navigated to Honda bikes page");
	}

	public void newPage(ExtentTest test) {

		test.log(Status.INFO, "Navigating to new bikes page");
	}

	public void usedPage(ExtentTest test) {

		test.log(Status.INFO, "Navigating to used cars page");
	}

	public void login(ExtentTest test) {

		test.log(Status.INFO, "Navigating to google sign in");
	}

	public void display(ExtentTest test, String statement) {

		test.log(Status.INFO, statement);
	}

	public void select(ExtentTest test, String statement) {

		test.log(Status.INFO, statement);
	}

}
