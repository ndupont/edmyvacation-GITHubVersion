/**
 * EllisDon,
 * Copyright 2010-2012, EllisDon., and individual contributors as indicated
 * by the @authors tag.
 *
 * This program is copyright protected and belongs to EllisDon. All rights are
 * reserved.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 */

package com.ellisdon.portal.mv.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * This class contains Utility methods for data formatting, type conversions, etc
 *
 * @author ahmedb
 * @version 1.0, Nov 30, 2010
 * @since JDK1.6
 */
public class Utility {
	public static final String EMPTY_STRING = ""; 
	
	/**
     * This method return true if the object is meaningfully null. 
     * If the input object is string then it is also checked if the string is empty.
     * If the input object is a collection it is checked if its size is greater than zero
     * @param obj The object which has to be checked for null
     * @return true if the obj is null
     */
    public static boolean isNull(Object obj){
       if(obj == null){
            return true;
       }
       else if(obj instanceof String){
            return (((String)obj).trim().length()==0);
       }
       else if(obj instanceof Collection){
            return (((Collection)obj).size() == 0);
       }
       else{
            return false;
       }
    }	
	
	 /**
	 * 
	 * @param dateValue
	 * @param dateOnlyFormat
	 * @return Date
	 * @throws ParseException
	 */
	public static Date convertString2Date(String dateValue,String dateFormat) throws ParseException{	
		if(Utility.isNull(dateValue)){
			return null;
		}
		Date date = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		try 
		{			
			date = new Date(simpleDateFormat.parse(dateValue).getTime());
			return date;
		}
		catch(ParseException e){			
			throw e;
		}
	}
	
	/**
	 * 
	 * @param dateValue
	 * @param dateFormat
	 * @return String
	 * @throws Exception
	 */
	public static String convertDate2String(Date dateValue,String dateFormat)throws Exception{
		if(Utility.isNull(dateValue)){
			return Utility.EMPTY_STRING;
		}
		try{	
			SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
			return formatter.format(dateValue);
		}
		catch(Exception e){			
			throw e;
		}
	}

	/**
	 * 
	 * @param dateValue
	 * @param dateFormat
	 * @return String
	 * @throws Exception
	 */
	public static String convertDate2String(Date dateValue,int dateFormat)throws Exception{
		if(Utility.isNull(dateValue)){
			return Utility.EMPTY_STRING;
		}
		try{	
			DateFormat formatter = DateFormat.getDateInstance(dateFormat);
			return formatter.format(dateValue);
		}
		catch(Exception e){			
			throw e;
		}
	}
	
	public static Calendar getOnlyDate(Date dt){
		
		try {			
		    DateFormat formatter = DateFormat.getDateInstance(DateFormat.MEDIUM);
  	     	String formattedDate = formatter.format(dt);
  	   		Date date = formatter.parse(formattedDate);
			Calendar cal = Calendar.getInstance();
			cal.clear();
			cal.setTime(date);
			return cal;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
     }

	
}
