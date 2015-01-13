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

package com.ellisdon.portal.mv.model;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.zul.Grid;
import org.zkoss.zul.GroupsModelArray;

import com.ellisdon.portal.mv.dto.VacationEntitlementDTO;
import com.ellisdon.portal.mv.dto.VacationHistoryDTO;
import com.ellisdon.portal.mv.service.VacationService;
import com.ellisdon.portal.mv.ui.VacationHistoryComparator;
import com.ellisdon.portal.mv.ui.VacationHistoryGroupsModelArray;
import com.ellisdon.portal.mv.util.CommanConstants;
import com.ellisdon.portal.mv.util.Utility;

/**
 * This is the Helper Bean which is used for the interaction between the Controller and Service layer.
 * It also hold intermediate data model state use by views. J2EE Pattern : ViewHelper/ValueBean.
 * This bean will be created and stored in the user session, and will be destroyed when user session 
 * expired or invalidated automatically.
 * 
 * @author ahmedb
 * @version 1.0, Dec 7, 2010
 * @since JDK1.6
 */

@Component("uservacBean")
@Scope("session")
public class UserVacationBean {

	private int userid;
	private Grid gridVacHistory;

	@Autowired(required=true)
	private VacationService vacServiceBean;
	
	public UserVacationBean(){
		System.out.println("UserVacation Bean Instance Created");
	}
	

	public int getUserEntitlementDays() {
		VacationEntitlementDTO vacentdto = vacServiceBean.getUserEntitlement(this.userid);
		return vacentdto.getEntitledays();		
	}
	
    public void updateUserEntitlement(int entdays, Date lastUpdate){
    	 	vacServiceBean.updateUserEntitlement(entdays, lastUpdate, this.userid);
    }
	
	public float getUserCarryOverDays() {
		VacationEntitlementDTO vacentdto = vacServiceBean.getUserEntitlement(this.userid);
		return vacentdto.getCarryoverdays();		
	}

	public String getUserEntLastUpdated() throws Exception{
		VacationEntitlementDTO vacentdto = vacServiceBean.getUserEntitlement(this.userid);
		String lastupdated = Utility.convertDate2String(vacentdto.getLastupdated(), CommanConstants.DATE_FORMAT_DISPLAY);
		return lastupdated;		
	}
	
	public GroupsModelArray getUserVacationHistory(){
		List<VacationHistoryDTO> vacHistDTOList = vacServiceBean.getUserVacationHistory(userid);
	    if (!Utility.isNull(vacHistDTOList) & (vacHistDTOList.size() > 0)){	      	
	    	return new VacationHistoryGroupsModelArray(vacHistDTOList.toArray(), new VacationHistoryComparator());
	    }
	    else{
	    	return null;	
	    }    	
    }
    
	public void cancelUserVacation(VacationHistoryDTO vachistdto){
		vacServiceBean.cancelVaction(vachistdto);
		refreshVacationHistoryGrid();
	}

	public void takeUserVacation(VacationHistoryDTO vachistdto){
		System.out.println("Current User Vacation Bean : " + this.userid);
		vachistdto.setUserid(this.userid);
		vacServiceBean.takeVaction(vachistdto);	
		refreshVacationHistoryGrid();
	}
	
	private void refreshVacationHistoryGrid(){
		gridVacHistory.setModel(getUserVacationHistory());
	}
	
	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setGridVacHistory(Grid gridVacHistory) {
		this.gridVacHistory = gridVacHistory;
	}
	
}
