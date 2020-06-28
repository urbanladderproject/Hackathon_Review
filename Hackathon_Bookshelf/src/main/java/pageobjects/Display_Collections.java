package pageobjects;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.BaseClass;
import utils.Excelutils;


public class Display_Collections {

	WebDriver driver;

	//assigns the xpath to the corresponding By classes
	@FindBy(xpath = "/html[1]/body[1]/div[1]/header[1]/div[2]/div[1]/nav[1]/div[1]/ul[1]/li[10]/div[1]/div[1]/ul[1]/li[1]/ul[1]")
	WebElement list_data;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/header[1]/div[2]/div[1]/nav[1]/div[1]/ul[1]/li[10]/span[1]")
	WebElement click_collections;

	Map<String, String> input_data;

	String[] info;


	public Display_Collections(WebDriver driver) throws Exception {
		this.driver = driver;
		this.input_data = Excelutils.readExcelData("test_input_data");
	}

	public void collection_click(String value) {
		
		value = input_data.get(value);
		
		System.out.println(value);
		
		if(value.equalsIgnoreCase("Collections"))
			BaseClass.doAction(driver, click_collections);
			//click_collections.click();
		else
			System.out.println("You have to select Collections menu but you have tried to select " + value);

	}


	public String[] getdata(String submenu) {

		submenu = input_data.get(submenu);
		
		System.out.println(submenu);
		
		if(submenu.equalsIgnoreCase("Being at home"))
		{
			System.out.println("Into get data");
			String Data = list_data.getText();
			
			System.out.println(Data);
			
			info = Data.split("\\r?\\n");	
			
			System.out.println(info);
		}

		else
			System.out.println("You have to choose the sub menu 'Being at Home', but you have trien to select " + submenu);
		
		return info;
	}

	public void displaydata() {

		int length = info.length;
		System.out.println("BEING AT HOME");
		for(int i=0;i<length;i++) 
		{
			System.out.println(info[i]);
		}		
	}

	public void write_result(String browser) throws Exception {
		

		//writes the results to an excel sheet respective to the browser
		Excelutils.writeExcelCollectionsData(info,browser);

	}

}