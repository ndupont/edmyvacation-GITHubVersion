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

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

import com.ellisdon.portal.mv.logger.MyVacationLogger;
import com.ellisdon.portal.mv.model.UserVacationBean;
import com.ellisdon.portal.mv.util.CommanConstants;
import com.ellisdon.portal.mv.util.StopWatch;
import com.ellisdon.portal.mv.util.Utility;

/**
 *This is the view controller for the VacationEntitelment view of MyVacation Portlet
 *
 * @author ahmedb
 * @version 1.0, Dec 9, 2010
 * @since JDK1.6
 */
public class VacationEntitelmentViewControllor extends GenericForwardComposer{

	private Listbox listEntDays;
	private Label lbEntUpdate;
	private Button btnEntUpdate;
	private StopWatch watch;
	
	private static final Logger logger = MyVacationLogger.getLogger(VacationEntitelmentViewControllor.class);
	
	@Override
	public ComponentInfo doBeforeCompose(Page page, Component parent,
			ComponentInfo compInfo) {
		logger.info(" *** Start Composing ... MyVacation Entitlement View.");
		watch = new StopWatch();
		return super.doBeforeCompose(page, parent, compInfo);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		refresh_screen();	    
	 }    
	
	public void onClick$btnEntUpdate(Event event) throws Exception{
	    UserVacationBean userVacation = (UserVacationBean) session.getAttribute(CommanConstants.USER_VACATION_HELPER_BEAN);
		int entdays = Integer.parseInt(listEntDays.getSelectedItem().getValue().toString());
		Date dt = new Date();
		java.util.Calendar cal = Utility.getOnlyDate(dt);
		userVacation.updateUserEntitlement(entdays, cal.getTime());	
		refresh_screen();
	}
	
	private void refresh_screen()throws Exception{
	    //Get handle to the UserVacationBean
	    UserVacationBean userVacation = (UserVacationBean) session.getAttribute(CommanConstants.USER_VACATION_HELPER_BEAN);

	    //Set the Entitlement days
	    int index=0;     
	    Integer userEntDays = userVacation.getUserEntitlementDays();
        String entdays = userEntDays.toString(); 
        List lst = listEntDays.getChildren();
		  for (int i=0; i< lst.size(); i++)
		  {
			 Listitem lstitm = (Listitem) lst.get(i);
			 if (lstitm.getValue().equals(entdays)){
			    index=i;
			    break;
			 }
		  }
	   listEntDays.setSelectedIndex(index);
	   lbEntUpdate.setValue(userVacation.getUserEntLastUpdated());
	}
	
	@Override
    public void doFinally() throws Exception {
    	super.doFinally();
    	logger.info(" *** Finished Composing ... MyVacation Entitlement View. Time taken in ms : " + watch.stopMillis());    	
    }
	
}
