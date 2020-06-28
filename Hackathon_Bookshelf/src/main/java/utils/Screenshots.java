package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshots {
	public WebDriver driver;

	public Screenshots() {
	}

	public Screenshots(WebDriver driver) {
		this.driver = driver;
	}

	public static void takesnap(WebDriver driver, String pathimg,String browser) throws IOException {
		String path = System.getProperty("user.dir");
		TakesScreenshot shot = ((TakesScreenshot) driver);
		File src = shot.getScreenshotAs(OutputType.FILE);
		File dest = new File(path + "//src//screenshot//" + browser +"//" + pathimg + ".jpg");
		FileUtils.copyFile(src, dest);
	}
}
