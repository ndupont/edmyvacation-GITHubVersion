package com.ellisdon.portal.mv.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ellisdon.portal.mv.dao.UserDAO;
import com.ellisdon.portal.mv.dto.UserDTO;
import com.ellisdon.portal.mv.exception.MyVacationRuntimeException;
import com.ellisdon.portal.mv.logger.MyVacationLogger;
import com.ellisdon.portal.mv.model.UserBean;
import com.ellisdon.portal.mv.service.UserService;
import com.ellisdon.portal.mv.util.*;

/**
 * This is the service class for Users
 *
 * @author ahmedb
 * @version 1.0, Nov 09, 2011
 * @since JDK1.6
 */

@Service("userServiceBean")
public class UserServiceImpl implements UserService{

	private static final Logger logger = MyVacationLogger.getLogger(UserServiceImpl.class);
	
	@Autowired(required=true)
	private UserDAO userDAO;
	
	@Transactional(readOnly=true)
	public UserBean validateUser(UserBean userbean) throws MyVacationRuntimeException {
		try{
			UserDTO userdto = userDAO.findUserbyEmail(userbean.getEmailaddress());
	    	  if (Utility.isNull(userdto)) {
	    		  userbean.setStatus(CommanConstants.USER_INVALID_EMAIL);
	    	  }else{
	    		  //Validate the user password
	    		  String pwd = userdto.getPassword();
	    		  if (pwd.equals(userbean.getUserpassword())){
	    			  userbean.setUserid(userdto.getUserid());
	    			  userbean.setName(userdto.getFname() + " " + userdto.getLname());
	    			  userbean.setStatus(CommanConstants.USER_VALID_STATUS);
	    			  
	    		  }else{  
	    			  userbean.setStatus(CommanConstants.USER_INVALID_PWD);
	    		  }
	    	  }
	    	  return userbean;
		}catch(DataAccessException dex) {
			logger.error("Data access exception occured : " + dex.getMessage(), dex);
			throw new MyVacationRuntimeException("Data access exception", dex);
		}catch(Exception ex){
			logger.error("Exception occured : " + ex.getMessage(), ex);
			throw new MyVacationRuntimeException(ex.getMessage(), ex);
		}
    }

}
