package com.plymouthrock.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import Locators.HomepageLocators;
import utility.Excel;

public class PlymouthRockHomePage extends HomepageLocators {

	public static WebDriver driver;
	
	public PlymouthRockHomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	
	public void getHomePage(String url) {
		driver.get(url);
	}
	
	public void clickAuto() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		autoImg.click();
		
	}
	public void enterZip(int row) {
		String data = Excel.getData(row, 0);
		zipCode.sendKeys(data);	
	}
	
	public WelcomePage clickGetQuote(WebDriver driver) {
		getQuote.click();
		return new WelcomePage(driver);
		
	}
}
