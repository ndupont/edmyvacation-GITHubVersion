package com.ellisdon.portal.mv.dao;

import org.springframework.dao.DataAccessException;

import com.ellisdon.portal.mv.dto.UserDTO;

/**
 * This is the Interface Class for User Data Access Object
 *
 * @author ahmedb
 * @version 1.0, Nov 28, 2010
 * @since JDK1.6
 */

public interface UserDAO {

	public UserDTO findUserbyEmail(String email) throws DataAccessException;
	
}
