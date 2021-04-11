package com.calculatetripcost.utils;

import java.util.Date;

public class TimeStamp 
{

   public static String date()
   {
      // Instantiate a Date object
      Date date = new Date();

      // display time and date using toString()
      return date.toString().replace(":"," ").replaceAll(" ", "_");
   }
}