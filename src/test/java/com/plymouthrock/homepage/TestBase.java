package com.plymouthrock.homepage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;



import utility.ExtentManager;
import utility.LoggerHelper;
import utility.ResourceHelper;

public class TestBase {
	public WebDriver Driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	private Logger log = LoggerHelper.getLogger(TestBase.class);
	public static File reportDirectory;
	@BeforeTest
	public WebDriver init_Driver() {
		
			
			if (Driver==null) {System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/main/resources/Drivers/chromedriver");

				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				options.addArguments("--incognito");
				options.addArguments("--disable-application-cache");
				options.addArguments("--disk-cache-size=0");
				options.addArguments("--disable-extensions");
				Driver=new ChromeDriver(options);
				System.out.println("method 00Started");
		return Driver;} else{ return Driver;}
	}
	@BeforeTest
	public void beforeTestReport() {
		extent = ExtentManager.getInstance();
		test= extent.createTest(getClass().getSimpleName());
		reportDirectory =new File(ResourceHelper.getResourcePath("Screenshots"));
	}
	
	@AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, result.getThrowable());
            String imagePath = captureScreen(result.getName(), Driver);
            test.addScreenCaptureFromPath(imagePath);
           
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, result.getName() + " is pass");
            String imagePath = captureScreen(result.getName(), Driver);
            test.addScreenCaptureFromPath(imagePath);
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, result.getThrowable());
        }
        log.info("**************" + result.getName() + "Finished *************** ");
        extent.flush();
        Driver.close();

    }
	
	
	public String captureScreen(String fileName, WebDriver driver) {
        if (driver == null) {
            log.info("driver is null..");
            return null;
        }
        if (fileName == "") {
            fileName = "blank";
        }
        Reporter.log("captureScreen method called");
        File destFile = null;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        File screFile = ((TakesScreenshot) Driver).getScreenshotAs(OutputType.FILE);
        try {
            destFile = new File(reportDirectory + "/" + fileName + "_" + formater.format(calendar.getTime()) + ".png");
            Files.copy(screFile.toPath(), destFile.toPath());
            Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath() + "'height='100' width='100'/></a>");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return destFile.toString();
    }
	
	public Object executeJS(String script) {
		JavascriptExecutor exe = (JavascriptExecutor)Driver;
		return exe.executeScript(script);
	}
	public Object executeJS2(String script, Object...args) {
		JavascriptExecutor exe = (JavascriptExecutor)Driver;
		return exe.executeScript(script,args);
	}
	public void closeDriverDuringTest() {
		Driver.close();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--incognito");
		options.addArguments("--disable-application-cache");
		options.addArguments("--disk-cache-size=0");
		options.addArguments("--disable-extensions");
		Driver = new ChromeDriver(options);
	}
	
	public void scrollToElement(WebElement element) {
		executeJS2("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
	}
	public void clickElement(WebElement element) {
		executeJS2("arguments[0].click();", element);
	}
}
