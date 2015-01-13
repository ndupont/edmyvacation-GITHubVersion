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

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Grid;

import com.ellisdon.portal.mv.logger.MyVacationLogger;
import com.ellisdon.portal.mv.model.UserVacationBean;
import com.ellisdon.portal.mv.ui.VacationHistoryRowRenderer;
import com.ellisdon.portal.mv.util.CommanConstants;
import com.ellisdon.portal.mv.util.StopWatch;

/**
 * This is the view controller for the VactionHistory view of MyVacation Portlet
 *
 * @author ahmedb
 * @version 1.0, Dec 2, 2010
 * @since JDK1.6
 */
public class VacationHistoryViewController extends GenericForwardComposer {

	private static final Logger logger = MyVacationLogger.getLogger(VacationHistoryViewController.class);
	private Grid gridVacHistory;
	private StopWatch watch;
	
	@Override
	public ComponentInfo doBeforeCompose(Page page, Component parent,
			ComponentInfo compInfo) {
		logger.info(" *** Start Composing ... Vacation History View.");
		watch = new StopWatch();
		return super.doBeforeCompose(page, parent, compInfo);
	}


	@Override
	public void doAfterCompose(Component comp) throws Exception {
	    super.doAfterCompose(comp);
    
	    //Get handle to the UserVacationBean
	    UserVacationBean userVacation = (UserVacationBean) session.getAttribute(CommanConstants.USER_VACATION_HELPER_BEAN);
	    userVacation.setGridVacHistory(gridVacHistory);

	    gridVacHistory.setModel(userVacation.getUserVacationHistory());
	    gridVacHistory.setRowRenderer(new VacationHistoryRowRenderer(userVacation));
   	}	
	
	@Override
    public void doFinally() throws Exception {
    	super.doFinally();
		logger.info(" *** Finished Composing ... Vacation History View. Time taken in ms : " + watch.stopMillis());    	
    }


}

