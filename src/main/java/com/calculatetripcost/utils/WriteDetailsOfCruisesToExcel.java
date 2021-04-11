package com.calculatetripcost.utils;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteDetailsOfCruisesToExcel
{
	public static File file;
    public static FileOutputStream fos;
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static int rowCount=0;
    	
    
    public void writeToExcel(String[] Overview,String[] languages) 
    {   
    	try
		{	
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet Sheet = workbook.createSheet("CruiseDetails");
    	
			//For writing Overview
			int x = 0;
			for(int i = 0; i<Overview.length; i++) 
			{
    		
				Row rows = Sheet.createRow(i);
    		
				Cell col1 = rows.createCell(0);
				col1.setCellValue(Overview[x]);
    		
				x++;
			}
			
			//For writing languages
			int y=0;
			int count=languages.length+Overview.length-1;
			for(int j=Overview.length;j<count;j++)
			{
				Row rows = Sheet.createRow(j);
	    		
				Cell col1 = rows.createCell(0);
				col1.setCellValue(languages[y]);
    		
				y++;
			}
    	
			//Setting the path, and using FileOutputStream to save and close the excel file once data is written.
			String path = System.getProperty("user.dir");
			FileOutputStream fout = new FileOutputStream(path + "\\Excel_Output_Test_Result\\Cruise_Details.xlsx");
			workbook.write(fout);
			fout.close();
			workbook.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}