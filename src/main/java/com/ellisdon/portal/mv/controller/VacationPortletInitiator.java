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

import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.util.Initiator;
import org.zkoss.zkplus.spring.SpringUtil;
//import org.zkoss.spring.SpringUtil;

import com.ellisdon.portal.mv.logger.MyVacationLogger;
import com.ellisdon.portal.mv.model.UserBean;
import com.ellisdon.portal.mv.model.UserVacationBean;
import com.ellisdon.portal.mv.util.CommanConstants;
import com.ellisdon.portal.mv.util.StopWatch;

/**
 * This is the Initiator class for My Vacation Portlet.
 * It will get the 'User' object of the currently logged in
 * LifeRay user and set it as an attribute in the current desktop   
 *
 * @author ahmedb
 * @version 1.0, Nov 11, 2011
 * @since JDK1.6
 */
public class VacationPortletInitiator implements Initiator {

	private static final Logger logger = MyVacationLogger.getLogger(VacationPortletInitiator.class);
	
	
	@Override
	public void doInit(Page page, Map arg) throws Exception {

		logger.info("Start Initializing ... My Vacation Window.");
		StopWatch watch = new StopWatch(); 
		
		//Desktop desktop = page.getDesktop();
		Session session = page.getDesktop().getSession();
		
		//Get handle to the currently logged in User
		UserBean user = (UserBean) session.getAttribute(CommanConstants.USER_BEAN);
		int userid = user.getUserid();
		
		//Setup the UserVacationBean for the logged-in user
		UserVacationBean uservacBean = (UserVacationBean) SpringUtil.getBean(CommanConstants.USER_VACATION_HELPER_BEAN);
		uservacBean.setUserid(userid);
		session.setAttribute(CommanConstants.USER_VACATION_HELPER_BEAN, uservacBean);

		
		logger.info("Finished Initializing ... My Vacation Window. Time taken in ms : " + watch.stopMillis());	
	}
	
	//@Override
	public void doAfterCompose(Page arg0) throws Exception {
	}

	//@Override
	public boolean doCatch(Throwable arg0) throws Exception {
		return false;
	}

	//@Override
	public void doFinally() throws Exception {
	}


}
