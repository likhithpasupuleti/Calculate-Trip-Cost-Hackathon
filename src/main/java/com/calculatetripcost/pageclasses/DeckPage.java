package com.calculatetripcost.pageclasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import com.calculatetripcost.utils.RunScriptWriteExcel;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.calculatetripcost.baseclass.BaseClass;
import com.calculatetripcost.utils.WriteDetailsOfCruisesToExcel;

public class DeckPage extends BaseClass
{
	public DeckPage(WebDriver driver, ExtentTest logger) 
	{
		super(driver, logger);
	}

	CruisesPage cp=new CruisesPage(driver,logger);
	WebDriver driver1=cp.clickSearchButton();
	
	//@FindBy(xpath="//div[@class='_30ZCn9lR']/div")
	//public static List<WebElement> overview;
	
	
	//Get Overview of the Deck
	public static String[] getOverview(WebDriver driver1,ExtentTest logger1)
	{
		try
		{    
			//Scroll Down
			JavascriptExecutor jse=(JavascriptExecutor)driver1;
			jse.executeScript("window.scrollBy(0,300);");
			RunScriptWriteExcel.write(24,6,"Pass");
			Thread.sleep(4000);
			String[] about_deck=new String[3];
			System.out.println("Overview");
			System.out.println("*******************************************************");
			int i=0;
			List<WebElement> overview=driver1.findElements(By.xpath("//div[@class='_30ZCn9lR']/div"));
			for(WebElement o:overview)
			{
				System.out.println(o.getText());
				about_deck[i]=o.getText();
				i++;
			}
			System.out.println(" ");
			RunScriptWriteExcel.write(25,6,"Pass");
			logger1.log(Status.PASS,"Overview of the Deck is displayed and it is stored in Excel file Cruise_Details.xlsx in Excel_Output_Test_Result folder");
			return about_deck;
		}
		catch(Exception e)
		{
			logger1.log(Status.FAIL,e.getMessage());
			Assert.fail(e.getMessage());
			RunScriptWriteExcel.write(24,6,"Fail");
			RunScriptWriteExcel.write(25,6,"Fail");
		}
		return null;
	}
	
	//@FindBy(xpath="(//ul[@class='_2lcHrbTn'])[3]/li/label/span[@class='_1wk-I7LS']")
	//public static List<WebElement> allLanguagesOffered;
	
	//Languages Offered
	public static String[] languagesOffered(WebDriver driver1,ExtentTest logger1)
	{
		try
		{
			//Scroll Down
			JavascriptExecutor jse=(JavascriptExecutor)driver1;
			jse.executeScript("window.scrollBy(0,1000);");
			List<WebElement> allLanguagesOffered=driver1.findElements(By.xpath("(//ul[@class='_2lcHrbTn'])[3]/li/label/span[@class='_1wk-I7LS']"));
			String[] about_languages=new String[3];
			System.out.println("Languages");
			System.out.println("*******************************************************");
			int j=0;
			for(WebElement l:allLanguagesOffered)
			{
				System.out.println(l.getText());
				about_languages[j]=l.getText();
				j++;
			}
			logger1.log(Status.PASS, "Languages Offered is displayed and stored in Excel file Cruise_Details.xlsx in Excel_Output_Test_Result folder");
			System.out.println(" ");
			RunScriptWriteExcel.write(26,6,"Pass");
			return about_languages;
		}
		catch(Exception e)
		{
			logger1.log(Status.FAIL,e.getMessage());
			Assert.fail(e.getMessage());
			RunScriptWriteExcel.write(26,6,"Fail");
		}
		return null;
	}
	
	public static void writeCruiseDetailsToExcel(WebDriver driver1,ExtentTest logger1)
	{
		try
		{
			logger1.log(Status.INFO, "Overview of the Deck and Languages Offered should be displayed in console and store in Excel file Cruise_Details.xlsx in Excel_Output_Test_Result");
			//Declaring object for class globally for Writing Output to the file Cruise_Details.xlsx
			WriteDetailsOfCruisesToExcel write=new WriteDetailsOfCruisesToExcel();
			write.writeToExcel(getOverview(driver1,logger1), languagesOffered(driver1,logger1));
			logger1.log(Status.PASS, "Overview of the Deck and Languages Offered is displayed in console and stored in Excel file Cruise_Details.xlsx in Excel_Output_Test_Result");
		}
		catch(Exception e)
		{
			logger1.log(Status.FAIL,e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

}
