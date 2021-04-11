package com.calculatetripcost.utils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager
{
	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports report;
	
	public static ExtentReports getReportInstance()
	{
		if(report==null)
		{
			htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"\\test-output\\Extent_Report_"+TimeStamp.date()+".html");
			report=new ExtentReports();
			report.attachReporter(htmlReporter);
			
			report.setSystemInfo("OS", "Windows 10");
			report.setSystemInfo("Environment", "UAT");
			report.setSystemInfo("Browser", "Chrome");
			
			htmlReporter.config().setDocumentTitle("UAT UI Automation Result");
			htmlReporter.config().setReportName("All headlines");
			htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		}
		return report;
	}

}
