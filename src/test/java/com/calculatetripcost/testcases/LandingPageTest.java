package com.calculatetripcost.testcases;


import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import com.calculatetripcost.baseclass.BaseClass;
import com.calculatetripcost.baseclass.Browser;

public class LandingPageTest extends Browser
{
	@BeforeClass(groups = "Smoke Test")
	public void openBrowser() 
	{
		try
		{
			logger = report.createTest("Landing Page Report");
			invokeBrowser("chrome");  //To work for Maven Test I declared chrome here
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
			waitFor(2);
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
			waitFor(4);
			nairobi_Search_Results_Page=landingpage.choose_Nairobi();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@AfterClass(groups = "Smoke Test")
	public void quit()
	{
		flushReports();
		quitBrowser();
	}

}
