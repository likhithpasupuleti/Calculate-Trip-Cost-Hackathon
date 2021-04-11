package com.calculatetripcost.pageclasses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.calculatetripcost.baseclass.BaseClass;
import com.calculatetripcost.utils.ReadCruiseDataFromExcel;

public class CruisesPage extends BaseClass
{
	//Constructor
	public CruisesPage(WebDriver driver,ExtentTest logger)
	{
		super(driver,logger);
	}
	
	//Declaring ReadCruiseDataFromExcel Class
	ReadCruiseDataFromExcel cruise_details=new ReadCruiseDataFromExcel();
	ArrayList<Object> cr=cruise_details.readCruiseDetails();  //Calling method readCruiseDetails() from ReadCruiseDataFromExcel, It returns Array of Object
	
	@FindBy(xpath="(//button[@class='_2pD4QP2f _1eTmGmd9 _2_RN6U_a'])[1]")
	public WebElement cruises_line;
	
	public void click_cruiseLine()
	{
		try
		{
			logger.log(Status.INFO,"Cruise Line should be clicked to select Cruise Line from the list of options given in drop down");
			cruises_line.click();
			reportPass("Cruise Line is clicked");
		}
		catch(Exception e)
		{
			reportFail(e.getMessage());
		}
	}
	
	@FindBy(xpath="//ul[@class='_1xRf1n6u']/li/span/span")
	public List<WebElement> cruiseLineList;
	
	//Choose Cruise Line
	public void choose_cruise_line()
	{
		try
		{
			logger.log(Status.INFO, "Value from Cruise Line should be selected");
			String cruise_line=String.valueOf(cr.get(0));        //For Fetching data from Cruise_User_Details.xlsx (Conversion from Object to String)
			for(WebElement c:cruiseLineList)
			{
				if(c.getText().contains(cruise_line))       //American Cruise Lines data Fetched
				{
					c.click();
					break;
				}
			}
			reportPass("Value from Cruise Line is selected");
		}
		catch(Exception e)
		{
			reportFail(e.getMessage());
		}
	}
	
	@FindBy(xpath="(//button[@class='_2pD4QP2f _1eTmGmd9 _2_RN6U_a'])[2]")
	public WebElement cruises_ship;
	
	//click cruise ship
	public void click_cruiseShip()
	{
		try
		{
			logger.log(Status.INFO,"Cruise Ship should be enabled and it should be clicked to select Cruise Ship from the list of options given in drop down");
			cruises_ship.click();
			reportPass("Cruise Ship is clicked");
		}
		catch(Exception e)
		{
			reportFail(e.getMessage());
		}
	}
	
	@FindBy(xpath="//ul[@class='_1xRf1n6u']/li/span/span")
	public List<WebElement> cruiseShipList;
	
	//Choose Cruise Ship
	public void choose_cruise_ship()
	{
		try
		{
			logger.log(Status.INFO, "Value from Cruise Ship should be selected");
			String cruise_ship=String.valueOf(cr.get(1));		//Conversion from Object to String
			for(WebElement cs:cruiseShipList)
			{
				if(cs.getText().contains(cruise_ship))       //American Constellation data is Fetched
				{
					cs.click();
					break;
				}
			}
			reportPass("Value from Cruise Ship is selected");
		}
		catch(Exception e)
		{
			reportFail(e.getMessage());
		}
	}
	
	@FindBy(css="span._2O1ErRJV:nth-child(1) > button")
	public WebElement searchButton;
	
	/*public DeckPage search_button()
	{
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(searchButton));
	    searchButton.click();
		return PageFactory.initElements(driver, DeckPage.class);
	}*/
	
	//Click Search Button
	public WebDriver clickSearchButton()
	{
		try
		{
			WebDriverWait wait=new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(searchButton));
			logger.log(Status.INFO, "Search Button should be enabled and it should be clicked");
		    searchButton.click();
		    reportPass("Search Button is enabled and it is clicked");
		    Thread.sleep(3000);
		    String parent=driver.getWindowHandle();
		    Set<String> set=new HashSet<String>();
			set=driver.getWindowHandles();
			
			Iterator<String> I1= set.iterator();
			logger.log(Status.INFO, "Driver should switch from Parent window to child window");
			while(I1.hasNext())
			{
				String child=I1.next();
				if (!parent.equalsIgnoreCase(child)) 
				{
					//driver=(RemoteWebDriver) driver.switchTo().window(child);
					driver=driver.switchTo().window(child);
					reportPass("Driver is switched from Parent window to child window");
					DeckPage.writeCruiseDetailsToExcel(driver,logger);
					break;
				}
			}
			return driver;
		}
		catch(Exception e)
		{
			reportFail(e.getMessage());
		}
		return null;
	}
	

}
