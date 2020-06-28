package setup;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import utils.BaseClass;

public class Environment_Setup extends BaseClass {

	protected static WebDriver driver;
    static Properties prop;
    
	public static WebDriver getDriver(String browser, String environment)  {

		// Creating object for driverSetup
		Driver_Setup setup = new Driver_Setup();
		try { 
			prop= loadProp();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Assigning environment for the testcases to run
		if (environment.equalsIgnoreCase("grid")) {
			System.out.println(environment);
			driver = setup.createDriverGrid(browser);
		}

		else if (environment.equalsIgnoreCase("local")) {
			System.out.println(environment);
			driver = setup.createDriver(browser);
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Maximizing the Browser Window
		driver.manage().window().maximize();

		// Navigating to the Official Website
		driver.get(prop.getProperty("url"));

		// Waiting for the page to load completely
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}
}
