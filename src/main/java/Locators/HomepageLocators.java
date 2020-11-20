package Locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomepageLocators {
	
	@FindBy(xpath ="//p[@id='cardAuto']")
	public WebElement autoImg;

	
	@FindBy(xpath="//input[@id='zipCode']")
	public WebElement zipCode;
	
	
	@FindBy(xpath="//button[@class='get-quote-btn']")
	public WebElement getQuote;
}
