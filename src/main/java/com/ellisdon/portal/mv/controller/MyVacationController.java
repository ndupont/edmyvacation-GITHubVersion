package com.ellisdon.portal.mv.controller;

import java.awt.Desktop;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.WebApps;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zk.ui.util.Statistic;
import org.zkoss.zul.Window;

import com.ellisdon.portal.mv.logger.MyVacationLogger;
import com.ellisdon.portal.mv.model.UserBean;
import com.ellisdon.portal.mv.util.CommanConstants;
import com.ellisdon.portal.mv.util.StopWatch;

/**
 * This is the view controller for MyVacation window
 *
 * @author ahmedb
 * @version 1.0, Nov 11, 2011
 * @since JDK1.6
 */

public class MyVacationController extends GenericForwardComposer {

	private static final Logger logger = MyVacationLogger.getLogger(MyVacationController.class);

	private Window winVacation;
	private StopWatch watch;
	
	@Override
	public ComponentInfo doBeforeCompose(Page page, Component parent,
			ComponentInfo compInfo) {
		logger.info("Start Composing ... MyVacation window.");
		watch = new StopWatch();
		return super.doBeforeCompose(page, parent, compInfo);
	}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {

		
		super.doAfterCompose(comp);
	    
		//Get handle to the currently logged in User
		UserBean user = (UserBean) session.getAttribute(CommanConstants.USER_BEAN);
		String username = user.getName();
		winVacation.setTitle("My Vacation : " + username);
		
	 }	

	public void onClick$signoutBtn(Event event) {
		Session session = Executions.getCurrent().getDesktop().getSession();
		session.removeAttribute(CommanConstants.USER_BEAN);
		session.removeAttribute(CommanConstants.USER_VACATION_HELPER_BEAN);
		session.invalidate();
		logMemStats();
		Executions.getCurrent().sendRedirect("signin.zul");		
	}

	public void onClick$memStatsBtn(Event event) {
		logMemStats();
	}
	
	
	@Override
    public void doFinally() throws Exception {
    	super.doFinally();
    	logger.info("Finished Composing ... MyVacation window. Time taken in ms : " + watch.stopMillis());    	
    }
	
	private void logMemStats(){
		Statistic statistic = (Statistic) Executions.getCurrent().getDesktop().getWebApp().getConfiguration().getMonitor();
		logger.info("Active Desktops : " + statistic.getActiveDesktopCount());
		logger.info("Active Sessions : " + statistic.getActiveSessionCount());
	}
	
}
