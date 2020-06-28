package pageobjects;

import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.Excelutils;

public class GiftCard {

	WebDriver driver;

	@FindBy(xpath = "//a[contains(text(),'Gift Cards')]")
	WebElement select_giftcard;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/main[1]/section[1]/section[1]/ul[1]/li[3]")
	WebElement select_birthdayoranniversary;

	@FindBy(id = "ip_2251506436")
	WebElement txt_amount;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/main[1]/section[1]/section[2]/div[1]/section[2]/button[1]")
	WebElement next;

	@FindBy(name = "recipient_name")
	WebElement receipent_name;

	@FindBy(name = "recipient_email")
	WebElement receipent_email;

	@FindBy(name = "customer_name")
	WebElement sender_name;

	@FindBy(name = "customer_email")
	WebElement sender_email;

	@FindBy(name = "customer_mobile_number")
	WebElement sender_phone;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/main[1]/section[1]/section[3]/form[1]/button[1]")
	WebElement btn_cnfrm;

	Map<String, String> input_data;

	public GiftCard(WebDriver driver) throws Exception {
		this.driver = driver;
		this.input_data = Excelutils.readExcelData("test_input_data");
	}

	public void enter_to_giftcard(String order) {

		order = input_data.get(order);
		
		if(order.equalsIgnoreCase("Gift Card")||order.equalsIgnoreCase("Gift Cards"))
			select_giftcard.click();

		else
			System.out.println("You have to order using gift card, but you have tried to order " + order);
	}

	public void select_gifttype(String occasion) {
		
		occasion = input_data.get(occasion);
		
		if(occasion.equalsIgnoreCase("Birthday/Anniversary"))
			select_birthdayoranniversary.click();
		else
			System.out.println("You have to select the 'Birthday/Anniversary' occasaion, but you have tried to choose " + occasion);
	}

	public void enter_amount(String amount) {

		amount = input_data.get(amount);
		
		int price = Integer.valueOf(amount);
				
		if(price >= 1000 && price <=500000)
			txt_amount.sendKeys(amount);
		else
			System.out.println("Your gift price should be greater than 1000 or less than 500000");
	}

	public void next_page() {
		next.click();
	}

	public void recepient_name(String name) {
		
		name = input_data.get(name);
		
		receipent_name.sendKeys(name);
		
	}

	public void recepient_email(String email) {
		
		email = input_data.get(email);
		
		receipent_email.sendKeys(email);
	}

	public void sender_name(String name) {
		
		name = input_data.get(name);
		
		sender_name.sendKeys(name);
	}

	public void sender_email(String email) {
		
		email = input_data.get(email);
		
		sender_email.sendKeys(email);
	}

	public void sender_phone(String phone) {
		
		phone = input_data.get(phone);
		
		sender_phone.sendKeys(phone);
	}

	public void confirm() {
		btn_cnfrm.click();
		System.out.println("Confirmed");
	}

	public String Alerts() {
		
		System.out.println("Alerts");
		
		Alert alert = driver.switchTo().alert();

		String warning = alert.getText();
		
		System.out.println(warning);
		
		alert.accept();

		return warning;
	}
}
