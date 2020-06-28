package setup;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver_Setup {

	private ThreadLocal<WebDriver> TL_driver = new ThreadLocal<WebDriver>();


	// Testcase execution locally
	public WebDriver createDriver(String browser) {
		System.out.println("Starting " + browser + " locally");

		// Creating driver
		//String path = System.getProperty("user.dir");
		switch (browser) {
		case "chrome":
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions option = new ChromeOptions();
			option.setExperimentalOption("prefs", prefs);

			WebDriverManager.chromedriver().setup();
			TL_driver.set(new ChromeDriver(option));
			break;

		case "firefox":

			WebDriverManager.firefoxdriver().setup();
			TL_driver.set(new FirefoxDriver());
			
			break;

		case "ie":
			WebDriverManager.iedriver().setup();
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			TL_driver.set(new InternetExplorerDriver());
			break;

		case "opera":
			WebDriverManager.operadriver().setup();
			TL_driver.set(new OperaDriver());
			break;
		}

		return TL_driver.get();
	}

	// Testcase execution in Selenium Grid
	public WebDriver createDriverGrid(String browser) {

		String nodeURL = "http://localhost:4445/wd/hub";
		DesiredCapabilities capabilities = new DesiredCapabilities();
		System.out.println("Starting " + browser + " on grid");

		// Creating driver
		switch (browser) {
		case "chrome":
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions option = new ChromeOptions();
			option.setExperimentalOption("prefs", prefs);
			capabilities.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
			break;

		case "firefox":
			capabilities.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
			break;

		case "ie":
			capabilities.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			break;

		case "opera":
			capabilities.setBrowserName(DesiredCapabilities.opera().getBrowserName());
			break;
		}

		

		return TL_driver.get();
	}
}
