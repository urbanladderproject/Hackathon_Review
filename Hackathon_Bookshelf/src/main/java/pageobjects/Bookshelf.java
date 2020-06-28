package pageobjects;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.BaseClass;
import utils.Excelutils;

public class Bookshelf{
	WebDriver driver;

	Map<String, String> input_data;
	
	int counter = 0;

	//assigns the xpath to the corresponding By classes	

	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/h2[1]/span[1]")
	WebElement searched_title;
	
	@FindBy(id = "filters_availability_In_Stock_Only")
	WebElement chk_stock;

	@FindBy(xpath = "//div[contains(text(),'Storage Type')]")
	WebElement storage_dropdown;

	@FindBy(xpath = "//input[@id='filters_storage_type_Open']")
	WebElement storage;

	String pn_1 = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[3]/ul[1]/li[";
	String pn_2 = "]/div[1]/div[5]/a[1]/div[1]/span[1]";
	String pn_2_2 = "]/div[1]/div[3]/a[1]/div[1]/span[1]";
	String pp_1 = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[3]/ul[1]/li[";
	String pp_2 = "]/div[1]/div[5]/a[1]/div[2]/span[1]";
	String pp_2_2 = "]/div[1]/div[3]/a[1]/div[2]/span[1]";

	public Bookshelf(WebDriver driver) throws Exception
	{
		this.driver = driver;
		this.input_data = Excelutils.readExcelData("test_input_data");

	}
	
	public String get_searched_title() {
		return searched_title.getText();
	}

	//stockdetails method is used to check the "Exclude Out of Stock" checkbox
	public void stockdetails() {

		//Check box exclude out of box is checked
		chk_stock.click();
		System.out.println("Excluded out of stock");

	}

	//storage_dropdown method is used the dropdown the types of storages in the webpage
	public void storage_dropdown() throws Exception {

		System.out.println("Into storage type");
		BaseClass.doAction(driver, storage_dropdown);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		JavascriptExecutor ex=(JavascriptExecutor)driver;
		
		ex.executeScript("arguments[0].click()", storage);

	}

	//select_open_storagetype method selects the open type bookshelves 
	public void select_storagetype(String type) {

		System.out.println("Select the type of storage");
		
		type = input_data.get(type);

		System.out.println(type);

	}

	public void getresult(String price, String browser) throws Exception, InterruptedException
	{
		
		price = input_data.get(price);
		
		int proprice = Integer.valueOf(price);

		if(proprice == 15000)
		{
			String[][] result = new String[5][2];
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			String productname_web,productprice_web;
			int productprice_int;
			//displays the first five mobile phone's name and price
			int j = 0;
			for(int i=1;j<3;i++)
			{			
				try
				{
					String productname=pn_1+i+pn_2;
					String productprice=pp_1+i+pp_2;
					productname_web= driver.findElement(By.xpath(productname)).getText().toString();
					productprice_web=driver.findElement(By.xpath(productprice)).getText().toString().substring(1);
					productprice_web = productprice_web.replaceAll("\\p{Punct}","");
					System.out.println(productname_web +" and the price of the product is : "+productprice_web);

				}
				catch(Exception ex)
				{
					String productname=pn_1+i+pn_2_2;
					String productprice=pp_1+i+pp_2_2;
					productname_web= driver.findElement(By.xpath(productname)).getText().toString();
					productprice_web=driver.findElement(By.xpath(productprice)).getText().toString().substring(1);
					productprice_web = productprice_web.replaceAll("\\p{Punct}","");
					System.out.println(productname_web +" and the price of the product is : "+productprice_web);

				}
				productprice_int = Integer.valueOf(productprice_web);
				if(productprice_int <= 15000)
				{
					result[j][0] = productname_web;
					result[j][1] = productprice_web;
					j++;

				}
				//Assert.assertTrue((Integer.valueOf(productprice_web)) < 30000);   
			}	

			
			
			//writes the results to an excel sheet respective to the browser
			Excelutils.writeExcelData(result,browser);
		

		}

		else
		{
			System.out.println("You have tried to search for products at a maximum range of " + proprice);
			System.out.println("But, as per your project instruction you have to search for bookshelf less than or equal to Rs.15000");
		}
		
		
	}
}
