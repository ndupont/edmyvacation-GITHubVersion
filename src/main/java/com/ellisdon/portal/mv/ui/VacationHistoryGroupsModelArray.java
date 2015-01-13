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

import java.util.Comparator;

import org.zkoss.zul.GroupsModelArray;

import com.ellisdon.portal.mv.dto.VacationHistoryDTO;
import com.ellisdon.portal.mv.util.Utility;

/**
 * This is the customized version of org.zkoss.zul.GroupsModelArray
 * to create header in the VacationHistory Grid the display of Total 
 * Vacations taken per year.
 *
 * @author ahmedb
 * @version 1.0, Dec 9, 2010
 * @since JDK1.6
 */
public class VacationHistoryGroupsModelArray extends GroupsModelArray{

	public VacationHistoryGroupsModelArray(Object[] data, Comparator cmpr) {
		super(data, cmpr);	
	}
	
	public VacationHistoryGroupsModelArray(Object[] data, Comparator cmpr, int col) {
		super(data, cmpr, col);	
	}

    //Create GroupHeader Data
    protected Object createGroupHead(Object[] groupdata, int index, int col) {
        //Calculate the total vacation days taken per year
    	String groupheader = null;
    	float totaldaysTaken = 0;
    	
    	try {
    	VacationHistoryDTO vachistdto=null;
    	for (Object data : groupdata) {  
    	     vachistdto = (VacationHistoryDTO) data; 
    	     totaldaysTaken = vachistdto.getDaystaken() + totaldaysTaken;    		
    	}
    	
    	String vacYear = Utility.convertDate2String(vachistdto.getFromdate(), "yyyy");
    	groupheader = vacYear + " [ Vacation Days Taken : " + totaldaysTaken + " ]";  
    	
       	}catch (Exception ex){
    		ex.printStackTrace();
    	}
    	return groupheader; 
    }
}
