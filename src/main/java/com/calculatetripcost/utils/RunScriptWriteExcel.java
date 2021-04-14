package com.calculatetripcost.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RunScriptWriteExcel 
{
	public static void write(int row,int column,String msg) 
	{
		try 
		{
			FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\Test_Case_RunScript\\TestCaseRunScript.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet("RUN SCRIPT");
			if(sheet.getRow(row).getCell(column).getStringCellValue().equals("NO RUN"))
			{
				if(msg.equals("Pass"))
				{
					sheet.getRow(row).getCell(column).setCellValue("PASS");
				}
				else
				{
					sheet.getRow(row).getCell(column).setCellValue("FAIL");
				}
				
			}
			FileOutputStream output_file=new FileOutputStream(System.getProperty("user.dir")+"\\Test_Case_RunScript\\TestCaseRunScript.xlsx");  //Open FileOutputStream to write updates
            
            workbook.write(output_file); //write changes
              
            output_file.close();  //close the stream  
			workbook.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
