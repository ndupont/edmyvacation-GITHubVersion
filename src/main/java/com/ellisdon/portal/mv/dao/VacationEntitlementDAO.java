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

package com.ellisdon.portal.mv.dao;

import java.util.Date;

import org.springframework.dao.DataAccessException;

import com.ellisdon.portal.mv.dto.VacationEntitlementDTO;

/**
 * This is the Interface Class for VacationEntitlement Data Access Object
 *
 * @author ahmedb
 * @version 1.0, Nov 28, 2010
 * @since JDK1.6
 */
public interface VacationEntitlementDAO {
	
	public VacationEntitlementDTO insertUserEntitlement(VacationEntitlementDTO vacentdto) throws DataAccessException;

	public VacationEntitlementDTO getUserEntitlement(int IDUser) throws DataAccessException;

	public void updateUserEntitlement(int entdays, Date lastUpdate, int UserID)  throws DataAccessException;	
	
}
