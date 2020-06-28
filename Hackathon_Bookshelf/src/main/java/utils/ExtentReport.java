package utils;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport {
	public static ExtentReports reports;
	public static ExtentTest log;
	
	

	@BeforeSuite(alwaysRun = true)
	public void reportSetUp() {
		ExtentHtmlReporter htmlreporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "//src//test//report//TestSuite Report.html");
		reports = new ExtentReports();
		reports.attachReporter(htmlreporter);
		reports.setSystemInfo("OS", "Windows");
		reports.setSystemInfo("Browser", "Chrome");
		htmlreporter.config().setDocumentTitle("Test Results");
		htmlreporter.config().setReportName("Report Consolidated");
		htmlreporter.config().setTimeStampFormat("MM-dd-yyyy HH:mm:ss");
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			log.fail(MarkupHelper.createLabel(result.getName() + " Test case failed", ExtentColor.RED));
			log.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			log.pass(MarkupHelper.createLabel(result.getName() + " Test case passed", ExtentColor.GREEN));

		} else {
			log.skip(MarkupHelper.createLabel(result.getName() + " Test case skipped", ExtentColor.YELLOW));
			log.skip(result.getThrowable());
		}

	}

	@AfterSuite(alwaysRun = true)
	public void flushReport() {
		reports.flush();
	}

}