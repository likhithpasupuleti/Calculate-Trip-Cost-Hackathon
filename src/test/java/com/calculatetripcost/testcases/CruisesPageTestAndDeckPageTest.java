package com.calculatetripcost.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import com.calculatetripcost.baseclass.BaseClass;
import com.calculatetripcost.baseclass.Browser;

public class CruisesPageTestAndDeckPageTest extends Browser
{
	@BeforeClass(groups = "Smoke Test")
	public void openBrowser() 
	{
		try
		{
			logger = report.createTest("Cruises Page and Deck Page Report");
			invokeBrowser();
			//remoteBrowser();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=1,groups="Smoke Test")
	public void check_url()
	{
		try
		{
			BaseClass pageBase = new BaseClass(driver, logger);
			PageFactory.initElements(driver, pageBase);
			landingpage=pageBase.getUrl();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=2,groups="Smoke Test")
	public void TestHolidayHomesButton()
	{
		try
		{
			landingpage.click_HolidayHomes();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=3,groups={"Regression Test","Smoke Test"})
	public void destination_test()
	{
		try
		{
			//Location Input Box->Regression   ,   Enter Button->Smoke Test
			landingpage.enterDestination();
			waitFor(3);
			nairobi_Search_Results_Page=landingpage.choose_Nairobi();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=4,groups="Smoke Test")
	public void clickCruisesLinkTextTest()
	{
		try
		{
			cruises_page=nairobi_Search_Results_Page.clickCruises();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=5,groups={"Regression Test","Smoke Test"})
	public void clickCruisesLineTest()
	{
		try
		{
			cruises_page.click_cruiseLine();
			cruises_page.choose_cruise_line();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=6,groups={"Regression Test","Smoke Test"})
	public void clickCruiseShipTest()
	{
		try
		{
			cruises_page.click_cruiseShip();
			cruises_page.choose_cruise_ship();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=7,groups={"SmokeTest","Regression Test"})                  //It also displays overview and languages offered on console and stores in excel
	public void clickSearchButton()
	{
		try
		{
			cruises_page.clickSearchButton();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@AfterClass(groups = "Smoke Test")   
	public void quit()
	{
		quitBrowser();
		flushReports();
	}

}
