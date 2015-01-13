package com.ellisdon.portal.mv.test.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ellisdon.portal.mv.model.UserBean;
import com.ellisdon.portal.mv.service.UserService;
import com.ellisdon.portal.mv.exception.MyVacationRuntimeException;

/**
 * This is JUnit test class for the VacationService class
 *
 * @author ahmedb
 * @version 1.0, Nov 9, 2011
 * @since JDK1.6
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext.xml"})
@Transactional

public class TestUserService {
	
	@Autowired
    private UserService userService;

	@Test
	public void testValidateUser() throws Exception{
		UserBean userbean = new UserBean();
		userbean.setEmailaddress("bilal@ellisdon.com");
		userbean.setUserpassword("bilal");
		userbean = userService.validateUser(userbean);
       	System.out.println("User validation result : " + userbean.getStatus());
       	assertTrue(true);
    }

}
