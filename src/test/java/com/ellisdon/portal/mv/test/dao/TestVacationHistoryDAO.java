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

package com.ellisdon.portal.mv.test.dao;

import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ellisdon.portal.mv.dao.VacationHistoryDAO;
import com.ellisdon.portal.mv.dto.VacationHistoryDTO;
import com.ellisdon.portal.mv.util.Utility;

/**
 * This is the JUnit test class for VacationHistoryDAO
 *
 * @author ahmedb
 * @version 1.0, Nov 29, 2010
 * @since JDK1.6
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext.xml"})
@Transactional
public class TestVacationHistoryDAO {

	@Autowired
    private VacationHistoryDAO vacHistDAO;
	
	@Test
	public void testGetUserVacationHistory(){
		 try {
				Integer userid = new Integer(1);
				List<VacationHistoryDTO> vacHistDTOList = vacHistDAO.getUserVacationHistory(userid);
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
	@Rollback(false)
	public void testUpdateUserVacationHistory(){
		 //try {
			 
			 assertTrue(true);

			 /*
				Integer vacHistId = new Integer(49);
				 VacationHistoryDTO vacHistDto = vacHistDAO.getVacationHistory(vacHistId);
	            if (vacHistDto != null){
	            	vacHistDto.setDaysnottaken(4);
	            	vacHistDAO.updateUserVacationHistory(vacHistDto);
	            	System.out.println("--------- testUpdateUserEntitlement() ---------");
	            	System.out.println("Updated Not Taken Days for vacation history : " + vacHistDto.getUservacationid());
	            	assertTrue(true);
	            }
	            else{
	            	assertTrue(false);
	            }
			  }catch(Exception ex){
				   ex.printStackTrace();	
	 			   assertTrue(false);
				}
				*/			
	}
	
	@Test
	@Rollback(false)
	public void testInsertVacationHistory(){
	
	  try {	
		VacationHistoryDTO vacHistDto = new VacationHistoryDTO();
		vacHistDto.setUserid(new Integer(2));
		//From Date
		Date fromDate = Calendar.getInstance().getTime();
		vacHistDto.setFromdate(fromDate);
		//To Date
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_WEEK, 3);
		Date toDate = c.getTime();
		vacHistDto.setTodate(toDate);
		vacHistDto.setDaystaken(4);
		vacHistDto.setDaysnottaken(0);
		vacHistDto.setComments("Junit Test");
		vacHistDAO.insertVacationHistory(vacHistDto);
		assertTrue(true);
	  }catch(Exception ex){
		   ex.printStackTrace();	
		   assertTrue(false);
  	  }			
	}
	
}
