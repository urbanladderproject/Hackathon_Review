package pageobjects;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Excelutils;

public class Url_navigating_homepage {

	public static WebDriver driver;

	Map<String, String> input_data;

	@FindBy(partialLinkText = "Close")
	static
	WebElement clearpage;

	@FindBy(id = "search")
	WebElement searchboxtext;

	@FindBy(xpath = "//span[@class='search-icon icofont-search']")
	WebElement btn_submit;

	public Url_navigating_homepage (WebDriver driver) throws Exception{
		Url_navigating_homepage.driver = driver;
		this.input_data = Excelutils.readExcelData("test_input_data");
	}

	//closes the login popup 
	public void clearpage() {
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		JavascriptExecutor ex=(JavascriptExecutor)driver;
		
		ex.executeScript("arguments[0].click()", clearpage);
		
		System.out.println("Page cleared to search");
	}

	//Searches for bookshelf
	public void searchText(String Search_Text) throws Exception {		

		String searchdata = input_data.get(Search_Text);
		WebElement toClear = searchboxtext;
		toClear.sendKeys(Keys.CONTROL + "a");
		toClear.sendKeys(Keys.DELETE);

		if(searchdata.equalsIgnoreCase("Bookshelf") || searchdata.equalsIgnoreCase("Open Bookshelf")  ||  searchdata.equalsIgnoreCase("Study Chair"))
		{
			searchboxtext.sendKeys(searchdata);
			System.out.println("searched");
		}

		else
			System.out.println("You can search for either bookshelf or study chair, but you have tried to search "+ searchdata);


	}   

	//Clicks on submit button
	public void searchbutton() {

		btn_submit.click();
		System.out.println("submited");

	}

}
