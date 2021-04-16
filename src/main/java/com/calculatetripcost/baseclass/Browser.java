package com.calculatetripcost.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.calculatetripcost.pageclasses.CruisesPage;
import com.calculatetripcost.pageclasses.DeckPage;
import com.calculatetripcost.pageclasses.LandingPage;
import com.calculatetripcost.pageclasses.NairobiSearchResultsPage;
import com.calculatetripcost.utils.ExtentReportManager;
import com.calculatetripcost.utils.RunScriptWriteExcel;


public class Browser
{
	public WebDriver driver;
	//public RemoteWebDriver driver;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;
	public Properties prop = new Properties();
	public LandingPage landingpage;
	public NairobiSearchResultsPage nairobi_Search_Results_Page;
	public CruisesPage cruises_page;
	public DeckPage deck_page;
	
	int flag=0;
	//Execution of Testcases on local browser
	public void invokeBrowser()
	{
		try
		{
			String browserName=property().getProperty("browser"); 				//Gets Browser Name from config.properties file
			if(browserName.equalsIgnoreCase("Chrome"))
			{
				flag=1;
				String driverPath = System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", driverPath);
				driver = new ChromeDriver();
				
				logger.log(Status.PASS,"Chrome Browser Successfully Launched");
				RunScriptWriteExcel.write(3,6,"Pass");
			}
			else if(browserName.equalsIgnoreCase("firefox"))
			{
				flag=2;
				
				String driverPath = System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe";
				System.setProperty("webdriver.gecko.driver", driverPath);
				driver =  new FirefoxDriver();
				
				logger.log(Status.PASS,"Fiefox Browser Successfully Launched");
				RunScriptWriteExcel.write(4,6,"Pass");
			}
			else if(browserName.equalsIgnoreCase("edge"))
			{
				flag=3;
						
				String driverPath = System.getProperty("user.dir") + "\\Drivers\\msedgedriver.exe";
				System.setProperty("webdriver.edge.driver", driverPath);
				driver = new EdgeDriver();
				
				logger.log(Status.PASS,"Edge Browser Successfully Launched");
				RunScriptWriteExcel.write(5,6,"Pass");
			}
			else
			{
				System.out.println("Invalid choice of browser");
			}
		}
		catch(Exception e)
		{
			if(flag==1)
			{
				RunScriptWriteExcel.write(3,6,"Fail");
			}
			else if(flag==2)
			{
				RunScriptWriteExcel.write(4,6,"Fail");
			}
			else if(flag==3)
			{
				RunScriptWriteExcel.write(5,6,"Fail");
			}
			
			logger.log(Status.FAIL,e.getMessage());
		}
	}
	
	//Flush Reports
	public void flushReports() 
	{
		report.flush();
	}
	
	//Quit the Driver
	public void quitBrowser()
	{
		driver.quit();
	}
	
	//Wait for Particular Seconds
	public void waitFor(int seconds) 
	{	
		try 
		{
			Thread.sleep(seconds * 1000);
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	//For Property File
	public Properties property()
	{
		try
		{
			String filelocation = System.getProperty("user.dir") + "\\ApplicationProperty\\config.properties";
			File file = new File(filelocation);
			FileInputStream fileinput = new FileInputStream(file);
			prop.load(fileinput);
			return prop;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}
