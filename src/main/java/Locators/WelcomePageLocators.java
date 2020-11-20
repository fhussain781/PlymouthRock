package Locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WelcomePageLocators{
	
	@FindBy(xpath="//input[@formcontrolname='addressLine1']")
	public WebElement address;
	
	@FindBy(xpath="//button[@class='next-btn col-sm-6 col-12']")
	public WebElement nextBtn;
	
	
	@FindBy(xpath="//input[@formcontrolname='firstName']")
	public WebElement firstname;
	

	@FindBy(xpath="//input[@formcontrolname='lastName']")
	public WebElement lastname;
	

	@FindBy(xpath="//input[@formcontrolname='birthDate']")
	public WebElement dateofBirth;
	

	@FindBy(xpath="//input[@formcontrolname='email']")
	public WebElement email;
	

	@FindBy(xpath="//input[@formcontrolname='homePhoneNumber']")
	public WebElement phone;

	
	@FindBy(xpath="//p[@class='quote']")
	public WebElement quoteNumber;
	
	@FindBy(xpath="//button[contains(text(),'Agree & Continue')]")
	public WebElement agreeBtn;
}
