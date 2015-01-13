package com.ellisdon.portal.mv.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ellisdon.portal.mv.dao.UserDAO;
import com.ellisdon.portal.mv.dto.UserDTO;
import com.ellisdon.portal.mv.dto.VacationEntitlementDTO;

/**
 * This is implementation class for VacationEntitlementDAO interface
 *
 * @author ahmedb
 * @version 1.0, Nov 09, 2011
 * @since JDK1.6
 */

@Repository("UserDAOBean")
public class UserDAOImpl implements UserDAO {
	
	@Autowired(required=true)
	private SessionFactory sessFactory;

	@Override
	public UserDTO findUserbyEmail(String email) throws DataAccessException {
		Session sess = sessFactory.getCurrentSession();
		UserDTO userdto = (UserDTO) sess.createQuery("from UserDTO as eduser where eduser.email = ?")
	    .setString(0, email).uniqueResult();
		return userdto;
	}
	
}
