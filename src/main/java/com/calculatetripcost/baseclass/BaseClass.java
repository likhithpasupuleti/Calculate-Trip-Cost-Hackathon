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
			
			driver.get(property().getProperty("url"));
			
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
	
	public static int j=0;
	
	/************************* Reporting Functions ****************************************/
	public void reportFail(String reportString) 
	{
		try 
		{
			logger.log(Status.FAIL,reportString);
			Assert.fail(reportString);
			takeScreenshot("Failure "+j);     			//Take Screenshot with Name Failure 0,Failure 1 etc
			logger.addScreenCaptureFromPath(System.getProperty("user.dir")+"\\Screenshots\\Failure "+j+".png");   //Add Screenshot to the Extent Report if any of the test fails
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
	public void takeScreenshotFailure(String name) 
	{
		try 
		{
			TakesScreenshot takeScreenshot=(TakesScreenshot)driver;
			File src=takeScreenshot.getScreenshotAs(OutputType.FILE);
			File dest=new File(System.getProperty("user.dir")+"\\Screenshots\\"+name+".png");
			FileUtils.copyFile(src, dest);
		}
	
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
