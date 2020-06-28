package utils;



import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry_Analyzer implements IRetryAnalyzer {

	private int retryCount = 1;
	private static final int maxRetryCount = 3;

	@Override
	public boolean retry(ITestResult result) {
		if (retryCount <= maxRetryCount) {
			System.out.println("Test Failed! Retry Attemnpt: " + retryCount);
			System.out.println("Retrying.....");
			retryCount++;
			return true;
		}
		return false;
	}
}