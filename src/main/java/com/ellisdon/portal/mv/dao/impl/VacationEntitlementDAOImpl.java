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

package com.ellisdon.portal.mv.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ellisdon.portal.mv.dao.VacationEntitlementDAO; 
import com.ellisdon.portal.mv.dto.VacationEntitlementDTO;

/**
 * This is implementation class for VacationEntitlementDAO interface
 *
 * @author ahmedb
 * @version 1.0, Nov 28, 2010
 * @since JDK1.6
 */
@Repository("vacEntitlementDAOBean")
public class VacationEntitlementDAOImpl implements VacationEntitlementDAO{
	
	@Autowired(required=true)
	private SessionFactory sessFactory;
	
	@Override
	public VacationEntitlementDTO getUserEntitlement(int UserID) throws DataAccessException {
		Session sess = sessFactory.getCurrentSession();
		VacationEntitlementDTO vacentdto = (VacationEntitlementDTO) sess.createQuery(
	    "from VacationEntitlementDTO as vacEnt where vacEnt.userid = ?")
	    .setInteger(0, UserID).uniqueResult();
		return vacentdto;
	}

	@Override
	public void updateUserEntitlement(int entdays,  Date lastUpdate, int UserID)  throws DataAccessException {
		Session sess = sessFactory.getCurrentSession();
		VacationEntitlementDTO vacentdto = (VacationEntitlementDTO) sess.createQuery(
	    "from VacationEntitlementDTO as vacEnt where vacEnt.userid = ?")
	    .setInteger(0, UserID).uniqueResult();
		vacentdto.setEntitledays(entdays);
		vacentdto.setLastupdated(lastUpdate);
		sess.merge(vacentdto);
	}
	
	
	@Override
	public VacationEntitlementDTO insertUserEntitlement(VacationEntitlementDTO vacentdto) throws DataAccessException {
		Session sess = sessFactory.getCurrentSession();
		sess.save(vacentdto);	
		return vacentdto;
	}
	
}
