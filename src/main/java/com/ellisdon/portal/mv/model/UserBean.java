package com.ellisdon.portal.mv.model;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ellisdon.portal.mv.exception.MyVacationRuntimeException;
import com.ellisdon.portal.mv.logger.MyVacationLogger;
import com.ellisdon.portal.mv.service.UserService;
import com.ellisdon.portal.mv.util.CommanConstants;

/**
 * This is the Helper Bean which is used for the interaction between the Controller and Service layer.
 * It also hold intermediate data model state use by views. J2EE Pattern : ViewHelper/ValueBean.
 * This bean will be created and stored in the user session, and will be destroyed when user session 
 * expired or invalidated automatically.
 * 
 * @author ahmedb
 * @version 1.0, Nov 9, 2011
 * @since JDK1.6
 */

@Component("userBean")
@Scope("session")
public class UserBean {

	private static final Logger logger = MyVacationLogger.getLogger(UserBean.class);
	
	private int userid;
	private String emailaddress;
	private String userpassword;
	private String status;
	private String name;
	
	@Autowired(required=true)
    private UserService userService;

	public UserBean(){
		System.out.println("UserBean Instance Created");
		this.status = CommanConstants.USER_INVALID_STATUS;
	}
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean validateUser(){
		printCurrentUser();
		try{
		    userService.validateUser(this);
		}catch(Exception ex){
			logger.error("Exception occured : " + ex.getMessage(), ex);
			throw new MyVacationRuntimeException(ex.getMessage(), ex);
		}
		if (this.getStatus().equals(CommanConstants.USER_VALID_STATUS)){
			return true;
		}else{
			return false;
		}
	}

	
	private void printCurrentUser(){
		System.out.println("Current User : " + this.getUserid()  + " -- " + this.getName() + " -- " + this.getEmailaddress());
	}
	
	
}
