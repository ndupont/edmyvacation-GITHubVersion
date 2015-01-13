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

package com.ellisdon.portal.mv.controller;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;

import com.ellisdon.portal.mv.dto.VacationHistoryDTO;
import com.ellisdon.portal.mv.logger.MyVacationLogger;
import com.ellisdon.portal.mv.model.UserVacationBean;
import com.ellisdon.portal.mv.util.CommanConstants;
import com.ellisdon.portal.mv.util.StopWatch;
import com.ellisdon.portal.mv.util.Utility;

/**
 * This is the view controller for the Vacation Entry view of My Vacation portlet
 *
 * @author ahmedb
 * @version 1.0, Dec 7, 2010
 * @since JDK1.6
 */
public class VacationEntryViewController extends GenericForwardComposer {
	
	private Datebox fromDate;
	private Datebox toDate;
	private Spinner daysTaken;
	private Textbox comments;
	private Checkbox halfDayCheck;
	private StopWatch watch;
	
	@Override
	public ComponentInfo doBeforeCompose(Page page, Component parent,
			ComponentInfo compInfo) {
		logger.info(" *** Start Composing ... Vacation Entry View.");
		watch = new StopWatch();
		return super.doBeforeCompose(page, parent, compInfo);
	}

	private static final Logger logger = MyVacationLogger.getLogger(VacationEntryViewController.class);
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
	    super.doAfterCompose(comp);	    
	    clearForm();
	 }	
	
	public void onClick$save(Event event) {
	    //Get handle to the UserVacationBean
	    UserVacationBean userVacation = (UserVacationBean) session.getAttribute(CommanConstants.USER_VACATION_HELPER_BEAN);
		
		if (!fromDate.isValid()){
			fromDate.setFocus(true);
			return;			
		}else if(!toDate.isValid()){		
			toDate.setFocus(true);
			return;
		}else if(!daysTaken.isValid()){		
			daysTaken.setFocus(true);
			return;
		}	
		
		//Validate FromDate and ToDate Sequence
	    validate_Dates();
	    
	    //Validate comments
	    validate_Comments();
	    
	    float takenDays = calculateDaysTaken();
		
		//Save the Vacation for User
		VacationHistoryDTO vachistdto = new VacationHistoryDTO();
		Calendar cal = Utility.getOnlyDate(fromDate.getValue());
		vachistdto.setFromdate(cal.getTime());
		cal = Utility.getOnlyDate(toDate.getValue());
	    vachistdto.setTodate(cal.getTime());
	    vachistdto.setDaystaken(takenDays);
	    vachistdto.setComments(comments.getText());		
	    userVacation.takeUserVacation(vachistdto);

	    //Clear the form
	    clearForm();
	}
	
	public void onClick$reset(Event event) {
		clearForm();
	}
	
	private void validate_Dates()throws WrongValueException{
	    Date date1 = fromDate.getValue();
	    Date date2 = toDate.getValue();
	
	    Calendar cal1 = Utility.getOnlyDate(date1);
	    Calendar cal2 = Utility.getOnlyDate(date2);
		if (cal1.after(cal2)) {			    
			throw new WrongValueException(fromDate, "From Date cannot be after To Date.");				
		}
	}
	
    private void validate_Comments() throws WrongValueException{
    	String val = comments.getValue().trim();
    	if (val.length() <= 0){
    		throw new WrongValueException(comments, "This field cannot be empty or contain only spaces.");
    	}
    	
    }
	
	private float calculateDaysTaken(){
		float dtaken = daysTaken.getValue();
		float totalDays=0;
		
		if ((!halfDayCheck.isChecked())&& (dtaken==0)) {
			throw new WrongValueException(daysTaken, "Please select vacation days.");	
		}else if((halfDayCheck.isChecked())&& (dtaken==0)){
			totalDays = new Float(0.5).floatValue(); 
		}else if((halfDayCheck.isChecked())&& (dtaken > 0)){
				totalDays = new Float(0.5).floatValue();
				totalDays = dtaken + totalDays; 
		}else{
			totalDays = dtaken;
		}
		return totalDays;		
	}
    
	private void clearForm(){
		fromDate.setValue(new Date());	
		toDate.setValue(new Date());
		daysTaken.setValue(0);
		comments.setValue("");	
		halfDayCheck.setChecked(false);
	}
	
	@Override
    public void doFinally() throws Exception {
    	super.doFinally();
    	logger.info(" *** Finished Composing ... Vacation Entry View. Time taken in ms : " +  + watch.stopMillis());    	
    }
	
}
