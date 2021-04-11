package com.calculatetripcost.utils;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadCruiseDataFromExcel 
{
	public ArrayList<Object> readCruiseDetails() 
	{
		ArrayList<Object> data=new ArrayList<Object>();
		try
		{
			String filePath =System.getProperty("user.dir")+"\\Excel_Input_Test_Data\\Cruise_User_Details.xlsx";
			FileInputStream file = new FileInputStream(filePath);
			XSSFWorkbook wb = new XSSFWorkbook(file);
			XSSFSheet ws=wb.getSheetAt(0);
			int lastCellNumber=ws.getRow(1).getLastCellNum(); //Only Row 1 is used. Row 0 consists of headings
			
			for(int i=0;i<lastCellNumber;i++)
			{
				data.add(ws.getRow(1).getCell(i));
			}
			wb.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return data;
	}
}
