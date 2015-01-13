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

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ellisdon.portal.mv.dao.VacationHistoryDAO;
import com.ellisdon.portal.mv.dto.VacationEntitlementDTO;
import com.ellisdon.portal.mv.dto.VacationHistoryDTO;

/**
 * This is implementation class for VacationHistoryDAO interface
 * 
 * @author ahmedb
 * @version 1.0, Nov 29, 2010
 * @since JDK1.6
 */
@Repository("vacHistoryDAOBean")
public class VacationHistoryDAOImpl implements VacationHistoryDAO{
	
	@Autowired(required=true)
	private SessionFactory sessFactory;
	
	@Override
	public List<VacationHistoryDTO> getUserVacationHistory(int IDUser)throws DataAccessException {
		Session sess = sessFactory.getCurrentSession();
		List<VacationHistoryDTO> vacHistDTOList = sess.createQuery(
	    "from VacationHistoryDTO as vacHist where vacHist.userid = ?")
	    .setInteger(0, IDUser)
	    .list();
         return vacHistDTOList;
     }

	@Override
	public void updateUserVacationHistory(VacationHistoryDTO vacHistDTO) throws DataAccessException {
		Session sess = sessFactory.getCurrentSession();
		sess.merge(vacHistDTO);
	}

	@Override
	public void insertVacationHistory(VacationHistoryDTO vacHistDTO) throws DataAccessException {
		Session sess = sessFactory.getCurrentSession();
		sess.save(vacHistDTO);		
	}

	@Override
	public VacationHistoryDTO getVacationHistory(int vacHistID) throws DataAccessException{
		VacationHistoryDTO vacHistDto = null;
		Session sess = sessFactory.getCurrentSession();
		vacHistDto = (VacationHistoryDTO) sess.load(VacationHistoryDTO.class, vacHistID); 
		return vacHistDto;		
	}
	
}
