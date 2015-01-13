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

package com.ellisdon.portal.mv.service;

import java.util.Date;
import java.util.List;

import com.ellisdon.portal.mv.dto.VacationEntitlementDTO;
import com.ellisdon.portal.mv.dto.VacationHistoryDTO;
import com.ellisdon.portal.mv.exception.MyVacationRuntimeException;

/**
 * This is the public interface for VacationHistory service
 *
 * @author ahmedb
 * @version 1.0, Dec 1, 2010
 * @since JDK1.6
 */
public interface VacationService {

	public List<VacationHistoryDTO> getUserVacationHistory(int IDUser) throws MyVacationRuntimeException;

	public void cancelVaction(VacationHistoryDTO vacHistDto) throws MyVacationRuntimeException;
	
	public void takeVaction(VacationHistoryDTO vacHistDto) throws MyVacationRuntimeException;
	
	public VacationEntitlementDTO getUserEntitlement(int IDUser) throws MyVacationRuntimeException;

	public void updateUserEntitlement(int entdays,  Date lastUpdate, int UserID) throws MyVacationRuntimeException;
	
}
