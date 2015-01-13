package com.ellisdon.portal.mv.controller;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zkplus.spring.SpringUtil;
//import org.zkoss.spring.SpringUtil;

import com.ellisdon.portal.mv.logger.MyVacationLogger;
import com.ellisdon.portal.mv.model.UserBean;
import com.ellisdon.portal.mv.util.CommanConstants;
import com.ellisdon.portal.mv.util.StopWatch;

/**
 * This is the view controller for the User Login view of My Vacation
 *
 * @author ahmedb
 * @version 1.0, Nov 8, 2011
 * @since JDK1.6
 */

public class LoginViewController extends GenericForwardComposer {
	
	private static final Logger logger = MyVacationLogger.getLogger(LoginViewController.class);

	private Textbox email;
	private Textbox passwrd;
	private Label lbmsg;
	private StopWatch watch;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
	    super.doAfterCompose(comp);
	    lbmsg.setVisible(false);
	    clearForm();
	 }	

	public void onClick$loginBtn(Event event) {

		 StopWatch watch = new StopWatch(); 
		
		//Validate Form
	    validate_form();
	    
	    //Validate User
	    UserBean userbean = (UserBean) SpringUtil.getBean(CommanConstants.USER_HELPER_BEAN);
	    userbean.setEmailaddress(email.getValue().trim());
	    userbean.setUserpassword(passwrd.getValue().trim());	    
	    if (userbean.validateUser()) {
			Session session = Executions.getCurrent().getDesktop().getSession();
			session.setAttribute(CommanConstants.USER_BEAN, userbean);
			Executions.getCurrent().sendRedirect("myvacation.zul");
	    } else{
			lbmsg.setValue(userbean.getStatus());
			lbmsg.setVisible(true);
	    }
	    
		logger.info("LoginViewController:SingInButton ... Execution time in ms : " +  + watch.stopMillis());
	}
	
	
	public void onClick$clearBtn(Event event) {
		clearForm();
	}
	
    private void validate_form() throws WrongValueException{
    	String val = email.getValue().trim();
    	if (val.length() <= 0){
    		throw new WrongValueException(email, "please enter email address.");
    	}
    	
    	val = passwrd.getValue().trim();
    	if (val.length() <= 0){
    		throw new WrongValueException(passwrd, "please enter password.");
    	}
    	
    }
	
	private void clearForm(){
		email.setValue("");	
		passwrd.setValue("");
		lbmsg.setVisible(false);
	}
	
}
