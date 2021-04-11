package com.calculatetripcost.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.calculatetripcost.pageclasses.LandingPage;
import com.calculatetripcost.utils.TimeStamp;

public class BaseClass extends Browser
{
	public ExtentTest logger;

	public BaseClass(WebDriver driver, ExtentTest logger) 
	{
		this.driver = driver;
		this.logger = logger;
	}
	
	//Open url
	public LandingPage getUrl() 
	{
		try
		{
			String filelocation = System.getProperty("user.dir") + "\\ApplicationProperty\\config.properties";
			File file = new File(filelocation);
			FileInputStream fileinput = new FileInputStream(file);
			Properties prop = new Properties();
			prop.load(fileinput);
			
			logger.log(Status.INFO, "Opening the url");
			
			driver.get(prop.getProperty("url"));
			
			reportPass("Successfully Opened the url");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
			
			LandingPage lp=new LandingPage(driver,logger);
			PageFactory.initElements(driver,lp);
			return lp;
		}
		catch(Exception e)
		{
			reportFail(e.getMessage());
		}
		return null;
	}  
	
	/************************* Reporting Functions ****************************************/
	public void reportFail(String reportString) 
	{
		try 
		{
			logger.log(Status.FAIL,reportString);
			takeScreenshotFailure();
			Assert.fail(reportString);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void reportPass(String reportString) 
	{
		try
		{
			logger.log(Status.PASS,reportString);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//take screenshot when any of the step did not passed
	public void takeScreenshotFailure() 
	{
		try 
		{
			TakesScreenshot takeScreenshot=(TakesScreenshot)driver;
			File src=takeScreenshot.getScreenshotAs(OutputType.FILE);
			File dest=new File(System.getProperty("user.dir")+"\\Screenshot\\"+TimeStamp.date()+".png");
			FileUtils.copyFile(src, dest);
		
			logger.addScreenCaptureFromPath(System.getProperty("user.dir")+"\\Screenshot\\"+TimeStamp.date()+".png");
		}
	
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}