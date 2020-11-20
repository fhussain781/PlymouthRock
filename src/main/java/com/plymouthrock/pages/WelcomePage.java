package com.plymouthrock.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import Locators.WelcomePageLocators;
import utility.Excel;

public class WelcomePage extends WelcomePageLocators{
	WebDriver driver;
	
	public WelcomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	public void enterAddress(int row) {
		
		String data = Excel.getData(row, 1);
		address.sendKeys(data);
	}
	
	public void clickNext(){
		
		nextBtn.click();
	}
	
	
public void enterFirstName(int row) {
		
		String data = Excel.getData(row, 2);
		firstname.sendKeys(data);
	}

public void enterLastName(int row) {
	
	String data = Excel.getData(row, 3);
	lastname.sendKeys(data);
}
public void enterBirthDate(int row) {
	
	String data = Excel.getData(row, 4);
	dateofBirth.sendKeys(data);
}
public void enterEmail(int row) {
	
	String data = Excel.getData(row, 5);
	email.sendKeys(data);
}
public void enterPhone(int row) {
	
	String data = Excel.getData(row, 6);
	phone.sendKeys(data);
}
	
public String getQuoteNumber() {
	return quoteNumber.getText();
}
}
