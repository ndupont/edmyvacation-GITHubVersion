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

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ellisdon.portal.mv.dto.VacationHistoryDTO;

/**
 * This is the Interface Class for VacationHistory Data Access Object
 *  
 * @author ahmedb
 * @version 1.0, Nov 29, 2010
 * @since JDK1.6
 */
public interface VacationHistoryDAO {

	public List<VacationHistoryDTO> getUserVacationHistory(int IDUser) throws DataAccessException;

	public void updateUserVacationHistory(VacationHistoryDTO vacHistDTO) throws DataAccessException;
	
	public void insertVacationHistory(VacationHistoryDTO vacHistDTO) throws DataAccessException;

	public VacationHistoryDTO getVacationHistory(int vacHistID) throws DataAccessException;
	
}
