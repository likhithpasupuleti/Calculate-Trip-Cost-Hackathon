package com.calculatetripcost.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.calculatetripcost.baseclass.BaseClass;
import com.calculatetripcost.baseclass.Browser;

public class NairobiSearchResultsPageTest extends Browser
{
	@BeforeClass(groups = "Smoke Test")
	public void openBrowser() 
	{
		try
		{
			logger = report.createTest("Nairobi Search Results Page Report");
			invokeBrowser("chrome");
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
	@Test(priority=4,groups="Regression Test")
	public void check_in_test()
	{
		try
		{
			nairobi_Search_Results_Page.click_check_in();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=5,groups="Regression Test")
	public void select_check_in_date_test()
	{
		try
		{
			nairobi_Search_Results_Page.choose_checkin_date();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=6,groups="Regression Test")
	public void check_out_test()
	{
		try
		{
			nairobi_Search_Results_Page.click_check_out();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=7,groups="Regression Test")
	public void select_check_out_date_test()
	{
		try
		{
			nairobi_Search_Results_Page.choose_checkout_date();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=8,groups={"Regression Test","Smoke Test"})
	public void ClickGuestsDropDownTest()
	{
		try
		{
			nairobi_Search_Results_Page.click_guests_dropdown();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=9,groups="Regression Test")
	public void chooseNumberOfGuestsTest()
	{
		try
		{
			nairobi_Search_Results_Page.choose_number_of_guests();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=10,groups="Smoke Test")
	public void clickApplyButtonTest()
	{
		try
		{
			nairobi_Search_Results_Page.click_apply_button();
			waitFor(3);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=11,groups="Regression Test")
	public void clickSortByTest()
	{
		try
		{
			nairobi_Search_Results_Page.click_Sort_By();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=12,groups="Regression Test")
	public void chooseSortBy()
	{
		try
		{
			nairobi_Search_Results_Page.chooseSortBy();
			waitFor(2);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=13,groups="Regression Test")
	public void clickMoreOptionsTest()
	{
		try
		{
			nairobi_Search_Results_Page.clickAmenitiesMoreOptions();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=14,groups="Regression Test")
	public void checkElevatorTest()
	{
		try
		{
			nairobi_Search_Results_Page.checkElevatorLiftAccess();
			waitFor(3);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=15,groups="Regression Test")
	public void writetoExcelTest()        //Displays HolidayHomesNames,ChargesPerNight,TotaCharges on console and also store in Excel file
	{
		try
		{
			nairobi_Search_Results_Page.WriteHotelDetailsToExcel();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(priority=16,groups={"Regression Test","Smoke Test"})
	public void clickOnHolidayHomesNameLinksTest()
	{
		try
		{
			nairobi_Search_Results_Page.clickThreeHolidayHomesLinks();
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
