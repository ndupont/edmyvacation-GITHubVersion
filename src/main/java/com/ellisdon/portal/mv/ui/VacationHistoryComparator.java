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

package com.ellisdon.portal.mv.ui;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import com.ellisdon.portal.mv.dto.VacationHistoryDTO;

/**
 * This is the comparator class to sort and group the user vacation history by years. 
 *
 * @author ahmedb
 * @version 1.0, Dec 8, 2010
 * @since JDK1.6
 */
public class VacationHistoryComparator implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
    	int result = 0;
    	
        VacationHistoryDTO vachistdto1 =(VacationHistoryDTO) o1;
        VacationHistoryDTO vachistdto2 =(VacationHistoryDTO) o2;
       
        Calendar cal = Calendar.getInstance();
        cal.setTime(vachistdto1.getFromdate());
        String year1 = Integer.toString(cal.get(Calendar.YEAR));
        
        cal.setTime(vachistdto2.getFromdate());
        String year2 = Integer.toString(cal.get(Calendar.YEAR));
        
        //Descending order
        return year2.toString().compareTo(year1.toString());    	
    } 
}
