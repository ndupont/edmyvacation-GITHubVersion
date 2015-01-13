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

package com.ellisdon.portal.mv.test.service;

import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ellisdon.portal.mv.dto.VacationEntitlementDTO;
import com.ellisdon.portal.mv.dto.VacationHistoryDTO;
import com.ellisdon.portal.mv.service.VacationService;
import com.ellisdon.portal.mv.util.Utility;

/**
 * This is JUnit test class for the VacationService class
 *
 * @author ahmedb
 * @version 1.0, Dec 2, 2010
 * @since JDK1.6
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext.xml"})
@Transactional
public class TestVacationService {
	
	@Autowired
    private VacationService vacService;
	
	@Test
	public void testGetUserVacationHistory(){
		 try {
				Integer userid = new Integer(1);
				List<VacationHistoryDTO> vacHistDTOList = vacService.getUserVacationHistory(userid);
	            if (vacHistDTOList != null){
	            	if (vacHistDTOList.size() > 0 ) {
	            		System.out.println(" ********* Vacation History For User ID : " + userid + "********");
	            		  for (int i=0; i< vacHistDTOList.size(); i++)
	            		  {
	            			  VacationHistoryDTO vacHistDto = (VacationHistoryDTO) vacHistDTOList.get(i);	            			  
	            			  System.out.println("Vacation ID : " + vacHistDto.getUservacationid());
	            			  String formattedFromDate = Utility.convertDate2String(vacHistDto.getFromdate(), "yyyy-MM-dd");
	            			  System.out.println("From : " + formattedFromDate);
	            			  String formattedToDate = Utility.convertDate2String(vacHistDto.getTodate(), "yyyy-MM-dd");
	            			  System.out.println("To : " + formattedToDate);
	            			  System.out.println("Days taken : " + vacHistDto.getDaysnottaken());
	            			  System.out.println("Days not taken : " + vacHistDto.getDaysnottaken());
	            			  System.out.println("Comments : " + vacHistDto.getComments());
	            			  System.out.println("");
	            		  }
	            	}else{
	            		System.out.println("Vacation History not found for userid : " + userid.toString());	
	            	}
	            	assertTrue(true);
	            }
	            else{
	            	assertTrue(false);
	            }
			  }catch(Exception ex){
				   ex.printStackTrace();	
	 			   assertTrue(false);
				}	
	}	
    

	@Test
	public void testGetUserEntitlement() throws Exception{
		Integer userid = new Integer(2);
		VacationEntitlementDTO  vacentdto = vacService.getUserEntitlement(userid);
        if (vacentdto != null){
        	System.out.println(" ********* Vacation Entitlemnt For User ID : " + userid.toString() + " ********");
        	System.out.println("Entitlement Days : " + vacentdto.getEntitledays());
        	System.out.println("Carry over Days : " + vacentdto.getCarryoverdays());
        	String formattedDate = Utility.convertDate2String(vacentdto.getLastupdated(), "yyyy-MM-dd");
        	System.out.println("Last updated : " + formattedDate);
        	assertTrue(true);
        }else{
        	assertTrue(false);
        }
    }
	
	
	
}
