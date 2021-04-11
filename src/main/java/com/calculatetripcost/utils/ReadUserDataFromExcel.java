package com.calculatetripcost.utils;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadUserDataFromExcel 
{
	//This function is used to get the path of the Excel File
	public static String getExcelPath() 
	{
		try
		{
			//Here path is getting the user directory, which is to be used to get the full path of the Excel file
			String path = System.getProperty("user.dir");
			String full_path = path + "\\Excel_Input_Test_Data\\Holiday_Homes_User_Details.xlsx";
			return full_path;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
		
	//This function is used to retrieve the number of guests from Excel Sheet
	@SuppressWarnings("incomplete-switch")
	public static int ReadNumberOfGuest() 
	{
		try
		{
			String SheetName = "UserDetails";
			int Number_of_Guest =0;;
			//FileInputStream is used to access our Excel File
			FileInputStream file = new FileInputStream(getExcelPath());
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
			int sheets = workbook.getNumberOfSheets();            //Count of sheets
			
			for(int i = 0 ; i < sheets; i++)
			{
				if(workbook.getSheetName(i).equalsIgnoreCase(SheetName)) 
				{
			        XSSFSheet sheet = workbook.getSheetAt(i);
			        Iterator<Row> rows = sheet.iterator();
			        //The firstrow contains the headings so we will not use it
			        @SuppressWarnings("unused")
					Row firstrow = rows.next();
			        //Selecting the second row which has our user details
			        Row Secondrow = rows.next();
			        Iterator<Cell> cells = Secondrow.cellIterator();
			        while(cells.hasNext()) 
			        {
			        	Cell input_value = cells.next();
			        	switch (input_value.getCellType())
		                {
			        		case NUMERIC:	
			        			Number_of_Guest = (int) input_value.getNumericCellValue();
			        			break;
		                }
			        }
				}
			}
			workbook.close();
			return Number_of_Guest;	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 1;
	}
		
	//This function is used to retrieve the Destination Location from Excel Sheet
	@SuppressWarnings("incomplete-switch")
	public static String ReadDestination()
	{
		try
		{
			String SheetName = "UserDetails";
			String Destination = "";
			//FileInputStream is used to access our Excel File
			FileInputStream ffs = new FileInputStream(getExcelPath());
			XSSFWorkbook workbook = new XSSFWorkbook(ffs);
			workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
			int sheets = workbook.getNumberOfSheets();
			
			for(int i = 0 ; i < sheets; i++)
			{
				if(workbook.getSheetName(i).equalsIgnoreCase(SheetName))
				{
			        XSSFSheet sheet = workbook.getSheetAt(i);
			        Iterator<Row> rows = sheet.iterator();
			        //The firstrow contains the headings so we will not use it
			        @SuppressWarnings("unused")
					Row firstrow = rows.next();
			        //Selecting the second row which has our user details
			        Row Secondrow = rows.next();
			        Iterator<Cell> cells = Secondrow.cellIterator();
			        while(cells.hasNext()) 
			        {
			        	Cell input_value = cells.next();
			        	switch (input_value.getCellType())
		                {
			        		case STRING:	
			        		//System.out.println(input_value.getStringCellValue());
			        		Destination = input_value.getStringCellValue();
			        		break;
		                }
			        }
				}
			}
			workbook.close();
			return Destination;	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
