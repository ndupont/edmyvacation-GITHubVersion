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

package com.ellisdon.portal.mv.util;

/**
 * This interface contains the common constants used by the application
 *
 * @author ahmedb
 * @version 1.0, Nov 11, 2011
 * @since JDK1.6
 */
public interface CommanConstants {

	static final String USER_BEAN = "USER";
	static final String SPRING_USER_SERVICE_BEAN = "userServiceBean";
	static final String SPRING_VACATION_SERVICE_BEAN = "vacationServiceBean";
	static final String USER_VACATION_HELPER_BEAN = "uservacBean";
	static final String USER_HELPER_BEAN = "userBean";
	
	static final String USER_INVALID_EMAIL = "Invalid Email address";
	static final String USER_INVALID_PWD = "Invalid Password";
	static final String USER_VALID_STATUS = "Valid User";
	static final String USER_INVALID_STATUS = "Invalid User";
	
	static final String DATE_FORMAT_DISPLAY = "yyyy-MMM-dd";
	
		
}
