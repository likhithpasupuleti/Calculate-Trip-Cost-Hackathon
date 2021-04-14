package com.calculatetripcost.baseclass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

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
	public Properties prop=new Properties();
	public LandingPage landingpage;
	public NairobiSearchResultsPage nairobi_Search_Results_Page;
	public CruisesPage cruises_page;
	public DeckPage deck_page;
	
	//Execution of Testcases on local browser
	public void invokeBrowser()
	{
		try
		{
			String browserName=property().getProperty("browser");
			if(browserName.equalsIgnoreCase("Chrome"))
			{
				String driverPath = System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", driverPath);
				driver = new ChromeDriver();
				
				logger.log(Status.PASS,"Chrome Browser Successfully Launched");
				RunScriptWriteExcel.write(3,6,"Pass");
			}
			else if(browserName.equalsIgnoreCase("edge"))
			{
				String driverPath = System.getProperty("user.dir") + "\\Drivers\\msedgedriver.exe";
				System.setProperty("webdriver.edge.driver", driverPath);
				driver = new EdgeDriver();
				
				logger.log(Status.PASS,"Edge Browser Successfully Launched");
				RunScriptWriteExcel.write(4,6,"Pass");
			}
			else if(browserName.equalsIgnoreCase("firefox"))
			{
				String driverPath = System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe";
				System.setProperty("webdriver.gecko.driver", driverPath);
				driver =  new FirefoxDriver();
				
				logger.log(Status.PASS,"Fiefox Browser Successfully Launched");
			}
			else if(browserName.equalsIgnoreCase("opera"))
			{
				String driverPath = System.getProperty("user.dir") + "\\Drivers\\operadriver.exe";
				System.setProperty("webdriver.opera.driver", driverPath);
				driver = new OperaDriver();
				
				logger.log(Status.PASS,"Opera Browser Successfully Launched");
			}
			else
			{
				System.out.println("Invalid choice of browser");
				RunScriptWriteExcel.write(5,6,"Pass");
			}
		}
		catch(Exception e)
		{
			logger.log(Status.FAIL,e.getMessage());
		}
	}
	
	//Execution of test  cases on Selenium Grid(i.e.,Parallel Execution)
	public void remoteBrowser() throws MalformedURLException
	{
		try
		{
			//1.Define Desired capabilties
			DesiredCapabilities cap=new DesiredCapabilities();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.WINDOWS);
				
			//2.Chrome Options Definition
			ChromeOptions options=new ChromeOptions();
			options.merge(cap);
				
			//3.Hub url and RemoteWebdriver
			String hubUrl="http://192.168.43.166:4444/wd/hub";   //Keeps on changing
			driver=new RemoteWebDriver(new URL(hubUrl),options);
		}
		catch(Exception e)
		{
			logger.log(Status.FAIL,e.getMessage());
		}
	}
	
	//@AfterSuite
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
		catch (InterruptedException e) 
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
