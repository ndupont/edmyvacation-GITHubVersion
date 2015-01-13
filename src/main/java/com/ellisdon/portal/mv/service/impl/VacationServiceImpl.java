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

package com.ellisdon.portal.mv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ellisdon.portal.mv.dto.VacationEntitlementDTO;
import com.ellisdon.portal.mv.dto.VacationHistoryDTO;
import com.ellisdon.portal.mv.dao.VacationEntitlementDAO;
import com.ellisdon.portal.mv.dao.VacationHistoryDAO;
import com.ellisdon.portal.mv.exception.MyVacationRuntimeException;
import com.ellisdon.portal.mv.logger.MyVacationLogger;
import com.ellisdon.portal.mv.service.VacationService;
import com.ellisdon.portal.mv.util.Utility;

/**
 * This is the single service class for MyVacation Portlet application
 *
 * @author ahmedb
 * @version 1.0, Dec 1, 2010
 * @since JDK1.6
 */
@Service("vacationServiceBean")
public class VacationServiceImpl implements VacationService{
	
	private static final Logger logger = MyVacationLogger.getLogger(VacationServiceImpl.class);
	
	@Autowired(required=true)
	private VacationHistoryDAO vacHistDAO;
	
	@Autowired(required=true)
	private VacationEntitlementDAO vacEntDAO;	
			
	@Transactional(readOnly=true)
	public List<VacationHistoryDTO> getUserVacationHistory(int IDUser) throws MyVacationRuntimeException {
	 List<VacationHistoryDTO> listVacHist;
	 try {		
		listVacHist = vacHistDAO.getUserVacationHistory(IDUser);
		return listVacHist;		
	 }catch(DataAccessException dex) {
 	     logger.error("Data access exception occured : " + dex.getMessage(), dex);
 	     throw new MyVacationRuntimeException("Data access exception", dex);
	 }catch(Exception ex){
 	     logger.error("Exception occured : " + ex.getMessage(), ex);
 	     throw new MyVacationRuntimeException(ex.getMessage(), ex);
	 }
	}

	@Transactional(readOnly=false)
	public void cancelVaction(VacationHistoryDTO vacHistDto) throws MyVacationRuntimeException {
      try {
		float daysTaken = vacHistDto.getDaystaken();
		vacHistDto.setDaysnottaken(daysTaken);
		vacHistDto.setDaystaken(0);
		vacHistDAO.updateUserVacationHistory(vacHistDto);
	 }catch(DataAccessException dex) {
 	     logger.error("Data access exception occured : " + dex.getMessage(), dex);
 	     throw new MyVacationRuntimeException("Data access exception", dex);
	 }catch(Exception ex){
 	     logger.error("Exception occured : " + ex.getMessage(), ex);
 	     throw new MyVacationRuntimeException(ex.getMessage(), ex);
	 }
	}
	
	@Transactional(readOnly=false)
	public void takeVaction(VacationHistoryDTO vacHistDto) throws MyVacationRuntimeException {
      try{
		vacHistDAO.insertVacationHistory(vacHistDto);
	 }catch(DataAccessException dex) {
 	     logger.error("Data access exception occured : " + dex.getMessage(), dex);
 	     throw new MyVacationRuntimeException("Data access exception", dex);
	 }catch(Exception ex){
 	     logger.error("Exception occured : " + ex.getMessage(), ex);
 	     throw new MyVacationRuntimeException(ex.getMessage(), ex);
	 }
	}

	@Transactional(readOnly=true)
	public VacationEntitlementDTO getUserEntitlement(int IDUser) throws MyVacationRuntimeException {
	      try{
	    	  VacationEntitlementDTO vacentdto = vacEntDAO.getUserEntitlement(IDUser);
	    	  if (Utility.isNull(vacentdto)) {
	    		  vacentdto = insertUserEntitlement(IDUser);
	    		  return vacentdto;
	    	  }else{
	    		  return vacentdto;
	    	  }    	 
	  	 }catch(DataAccessException dex) {
	   	     logger.error("Data access exception occured : " + dex.getMessage(), dex);
	   	     throw new MyVacationRuntimeException("Data access exception", dex);
	  	 }catch(Exception ex){
	   	     logger.error("Exception occured : " + ex.getMessage(), ex);
	   	     throw new MyVacationRuntimeException(ex.getMessage(), ex);
	  	 }
	}

	@Transactional(readOnly=false)
	public void updateUserEntitlement(int entdays,  Date lastUpdate, int UserID) throws MyVacationRuntimeException{
	      try{
		      vacEntDAO.updateUserEntitlement(entdays, lastUpdate, UserID);
		  }catch(DataAccessException dex) {
		   	  logger.error("Data access exception occured : " + dex.getMessage(), dex);
		   	  throw new MyVacationRuntimeException("Data access exception", dex);
		  }catch(Exception ex){
		   	  logger.error("Exception occured : " + ex.getMessage(), ex);
		   	  throw new MyVacationRuntimeException(ex.getMessage(), ex);
		  }
	}
	
	private VacationEntitlementDTO insertUserEntitlement(int IDUser) throws MyVacationRuntimeException {
		VacationEntitlementDTO vacentdto = new VacationEntitlementDTO();
		vacentdto.setUserid(IDUser);
		vacentdto.setEntitledays(15);
		vacentdto.setCarryoverdays(0);
		Date dt = new Date();
		java.util.Calendar cal = Utility.getOnlyDate(dt);
		vacentdto.setLastupdated(cal.getTime());
		vacEntDAO.insertUserEntitlement(vacentdto);
		return vacentdto;
	}
	
}
