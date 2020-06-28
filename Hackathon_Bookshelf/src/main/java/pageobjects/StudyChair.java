package pageobjects;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Excelutils;

public class StudyChair {
	String pn_1 = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[3]/ul[1]/li[";
	String pn_2 = "]/div[1]/div[5]/a[1]/div[1]/span[1]";
	String pn_2_2 = "]/div[1]/div[3]/a[1]/div[1]/span[1]";
	String pp_1 = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[3]/ul[1]/li[";
	String pp_2 = "]/div[1]/div[5]/a[1]/div[2]/span[1]";
	String pp_2_2 = "]/div[1]/div[3]/a[1]/div[2]/span[1]";
	WebDriver driver;
	Map<String, String> input_data;

	public StudyChair(WebDriver driver) throws Exception
	{
		this.driver = driver;
		this.input_data = Excelutils.readExcelData("test_input_data");
	}

	public void getresult(String browser,String sortby) throws Exception, InterruptedException
	{		
		
		sortby = input_data.get(sortby);
		
		if(sortby.equalsIgnoreCase("Recommended"))
		{
			String[][] result = new String[3][2];
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			String productname_web,productprice_web;

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
				result[j][0] = productname_web;
				result[j][1] = productprice_web;
				j++;

				//Assert.assertTrue((Integer.valueOf(productprice_web)) < 30000);   
			}	

			//writes the results to an excel sheet respective to the browser
			Excelutils.writeExcelStudyChairData(result,browser);			

		}
		else
			System.out.println("You have to sort the result by highly recommended order, but you have tried to sort by " + sortby);
	}


}
