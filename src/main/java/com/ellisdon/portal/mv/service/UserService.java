package com.ellisdon.portal.mv.service;

import com.ellisdon.portal.mv.exception.MyVacationRuntimeException;
import com.ellisdon.portal.mv.model.UserBean;

/**
 * This is the public interface for User service
 *
 * @author ahmedb
 * @version 1.0, Nov 09, 2011
 * @since JDK1.6
 */

public interface UserService {

	public UserBean validateUser(UserBean userbean) throws MyVacationRuntimeException;
	
}
