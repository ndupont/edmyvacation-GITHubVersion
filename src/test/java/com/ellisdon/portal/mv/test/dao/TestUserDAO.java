package com.ellisdon.portal.mv.test.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ellisdon.portal.mv.dao.UserDAO;
import com.ellisdon.portal.mv.dao.VacationEntitlementDAO;
import com.ellisdon.portal.mv.dto.UserDTO;
import com.ellisdon.portal.mv.dto.VacationEntitlementDTO;
import com.ellisdon.portal.mv.util.Utility;

/**
 * This is the JUnit test class for VacationEntitlementDAO
 *
 * @author ahmedb
 * @version 1.0, Nov 09, 2011
 * @since JDK1.6
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext.xml"})
@Transactional
public class TestUserDAO {
			
	@Autowired
    private UserDAO userDAO;
	
	@Test
	public void testFindUserbyEmail(){
		 try {
				String useremail = "bilal@ellisdon.com";
				UserDTO userDTO = userDAO.findUserbyEmail(useremail);
	            if (userDTO != null){	            
	            	System.out.println("--------- testFindUserbyEmail() ---------");
	            	System.out.println("User Record for Email Address : " + useremail);
	            	System.out.println("Name : " + userDTO.getFname() + " " + userDTO.getLname());
	            	System.out.println("password : " + userDTO.getPassword());
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
