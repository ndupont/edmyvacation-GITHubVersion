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

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ellisdon.portal.mv.dao.VacationEntitlementDAO;
import com.ellisdon.portal.mv.dto.VacationEntitlementDTO;
import com.ellisdon.portal.mv.util.Utility;

/**
 * This is the JUnit test class for VacationEntitlementDAO
 *
 * @author ahmedb
 * @version 1.0, Nov 28, 2010
 * @since JDK1.6
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext.xml"})
@Transactional
public class TestVacationEntitlementDAO {

	@Autowired
    private VacationEntitlementDAO vacEntDAO;

	@Test
	public void testGetUserEntitlement(){
		 try {
				Integer userid = new Integer(1);
				VacationEntitlementDTO vacEntDTO = vacEntDAO.getUserEntitlement(userid);
	            if (vacEntDTO != null){	            
	            	System.out.println("--------- testGetUserEntitlement() ---------");
	            	System.out.println("Entitlement Days : " + vacEntDTO.getEntitledays());
	            	System.out.println("Carry over Days : " + vacEntDTO.getCarryoverdays());
	            	String formattedDate = Utility.convertDate2String(vacEntDTO.getLastupdated(), "yyyy-MM-dd");
	            	System.out.println("Last updated : " + formattedDate);
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
	public void testUpdateUserEntitlement(){
		 try {
				Integer userid = new Integer(2);
				Date dt = new Date();
				java.util.Calendar cal = Utility.getOnlyDate(dt);
                vacEntDAO.updateUserEntitlement(25, cal.getTime(), 2);
				VacationEntitlementDTO vacEntDTO = vacEntDAO.getUserEntitlement(userid);
	            if (vacEntDTO != null){
	            	//vacEntDTO.setEntitledays(25);	            	
	            	System.out.println("--------- testUpdateUserEntitlement() ---------");
	            	System.out.println("Updated Entitlement Days : " + vacEntDTO.getEntitledays());
	            	String formattedDate = Utility.convertDate2String(vacEntDTO.getLastupdated(), "yyyy-MM-dd");
	            	System.out.println("Last updated : " + formattedDate);
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
	public void testInsertUserEntitlement(){
		 try {
				VacationEntitlementDTO vacentdto = new VacationEntitlementDTO();
				vacentdto.setUserid(3);
				vacentdto.setEntitledays(15);
				vacentdto.setCarryoverdays(0);
				Date dt = new Date();
				java.util.Calendar cal = Utility.getOnlyDate(dt);
				vacentdto.setLastupdated(cal.getTime());
				VacationEntitlementDTO vacentdtoRes = vacEntDAO.insertUserEntitlement(vacentdto);
				if (!Utility.isNull(vacentdtoRes)){
					System.out.println("User Entitlement Inserted for user  : 3");
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
	
}
