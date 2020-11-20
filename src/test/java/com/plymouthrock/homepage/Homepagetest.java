package com.plymouthrock.homepage;

import org.testng.annotations.Test;
import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.plymouthrock.pages.PlymouthRockHomePage;
import com.plymouthrock.pages.WelcomePage;

import utility.Excel;

public class Homepagetest extends TestBase{
	
	String url = "https://www.plymouthrock.com";
	

	
@Test
public void readExcel() throws InterruptedException, IOException {
	
	Driver.manage().window().maximize();
	for(int i =1; i<=Excel.lastRowNumber();i++) {
		PlymouthRockHomePage home = new PlymouthRockHomePage(Driver);
	home.getHomePage(url);
	Thread.sleep(1000);
	test.log(Status.INFO, "Navigating to "+ url);
	
	home.clickAuto();
	Thread.sleep(1000);
	test.addScreenCaptureFromPath(captureScreen("ClickedAuto"+i,Driver));
	
	home.enterZip(i);
	Thread.sleep(1000);
	test.addScreenCaptureFromPath(captureScreen("EnterZip"+i,Driver));
	
	WelcomePage welcomePage = home.clickGetQuote(Driver);
	Thread.sleep(2000);
	test.addScreenCaptureFromPath(captureScreen("ClickGetQuote"+i,Driver));
	
	welcomePage.enterAddress(i);
	Thread.sleep(2000);
	test.addScreenCaptureFromPath(captureScreen("address"+i,Driver));
	
	
	clickElement(welcomePage.nextBtn);
	Thread.sleep(2000);
	test.addScreenCaptureFromPath(captureScreen("clickNext"+i,Driver));
	
	test.log(Status.PASS, "Working on Quote: "+welcomePage.getQuoteNumber());
	welcomePage.enterFirstName(i);
	welcomePage.enterLastName(i);
	welcomePage.enterBirthDate(i);
	welcomePage.enterEmail(i);
	welcomePage.enterPhone(i);
	clickElement(welcomePage.agreeBtn);
	
	
	Thread.sleep(5000);
	if(i!= Excel.lastRowNumber()) {
		closeDriverDuringTest();
	}
}
	
}
}