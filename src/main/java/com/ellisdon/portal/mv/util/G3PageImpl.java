package com.ellisdon.portal.mv.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Richlet;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.impl.PageImpl;
import org.zkoss.zk.ui.metainfo.ComponentDefinitionMap;
import org.zkoss.zk.ui.metainfo.LanguageDefinition;
import org.zkoss.zk.ui.metainfo.PageDefinition;
import org.zkoss.zk.ui.util.DesktopActivationListener;
import org.zkoss.zk.ui.util.DesktopSerializationListener;
import org.zkoss.zk.ui.util.SessionActivationListener;
import org.zkoss.zk.ui.util.SessionSerializationListener;
import org.zkoss.zul.Window;

import com.ellisdon.portal.mv.logger.MyVacationLogger;

/**
 *
 * @author LGS/ndupont
 * @version 1.0 2011-10-12
 */
public class G3PageImpl extends PageImpl implements DesktopSerializationListener,DesktopActivationListener,SessionActivationListener,SessionSerializationListener{
	private static final long serialVersionUID = 8412496767072054871L;
	protected static final Logger logger = MyVacationLogger.getLogger(G3PageImpl.class);

	/**
	 * Constructor of G3PageImpl
	 * @param pgdef
	 */
	public G3PageImpl(PageDefinition pgdef) {
		super(pgdef);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor of G3PageImpl
	 * @param richlet
	 * @param path
	 */
	public G3PageImpl(Richlet richlet, String path) {
		super(richlet, path);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor of G3PageImpl
	 * @param langdef
	 * @param compdefs
	 * @param path
	 * @param zslang
	 */
	public G3PageImpl(LanguageDefinition langdef, ComponentDefinitionMap compdefs, String path, String zslang) {
		super(langdef, compdefs, path, zslang);
		// TODO Auto-generated constructor stub
	}

	private synchronized void writeObject(java.io.ObjectOutputStream s)
	throws java.io.IOException {
		s.defaultWriteObject();

		//write children
		for (AbstractComponent p = (AbstractComponent)getFirstRoot(); p != null; p = (AbstractComponent)(((AbstractComponent)p).getNextSibling())){
			s.writeObject(p);
		}
		s.writeObject(null);
	}
	public void printPageSize(){
		 //if(GatethreeProperties.getValue("dev.cluster.debug.enable", "false").equalsIgnoreCase("true")){
			logger.info("start page("+getId()+") breakdown estimate...");
			for (AbstractComponent p = (AbstractComponent)getFirstRoot(); p != null; p = (AbstractComponent)(((AbstractComponent)p).getNextSibling())){
				printComponentSize(p,0);
			}
			logger.info("...page("+getId()+") breakdown estimated with success ");
		//}
	}
	private void printComponentSize(AbstractComponent comp, int level){
		try{
			int maxLevelToDisplay=20;//max depth in composants tree. Deepers componants will not be estimated individually 
			int minComponentSizeToLog=100;//50;//in kb //Components < minComponentSizeToLog kB will not be displayed
			int minLeafComponentToLog=50;//5;//in kb //Leaf components < minLeafComponentToLog kB will not be displayed
	    	int idxbegin=0;
	    	int idxend=0;
			//try to determine the component size by writing it in memory
			//oas.defaultWriteObject(); //can/t be called here
	    	if(level<=maxLevelToDisplay){
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				G3ObjectOutputStream oas = new G3ObjectOutputStream(baos);
	    		idxbegin=baos.size();
				oas.writeObject(comp);
				idxend=baos.size();
				baos.close();
				int componentSizeInKb = (idxend-idxbegin)/1024;
				String padding = "";
				for(int i=0;i<level;i++){
					padding += "---";
				}
				List<AbstractComponent> children = comp.getChildren();
				if(level==0){
					logger.info("component "+padding+"+->("+comp.getClass().getName()+"/"+comp.getId()+") size: "+componentSizeInKb+ "kB (level root)" );
				}else if(comp instanceof Window){
					logger.info("component "+padding+"+->("+comp.getClass().getName()+"/"+comp.getId()+") size: "+componentSizeInKb+ "kB (Window)");
				}else if(comp.getListenerIterator("onEvent").hasNext()){
					logger.info("component "+padding+"+->("+comp.getClass().getName()+"/"+comp.getId()+") size: "+componentSizeInKb+ "kB (with composer)");
				}else if(componentSizeInKb>=minComponentSizeToLog){
					logger.info("component "+padding+"+->("+comp.getClass().getName()+"/"+comp.getId()+") size: "+componentSizeInKb+ "kB (>"+minComponentSizeToLog+"kB)");
				}else if(children.size()==0 && componentSizeInKb>=minLeafComponentToLog){
					logger.info("component "+padding+"+->("+comp.getClass().getName()+"/"+comp.getId()+") size: "+componentSizeInKb+ "kB (>"+minLeafComponentToLog+"kB)");
				}
				for (AbstractComponent p : children){
					printComponentSize(p,level+1);
				}
	    	}
			//End of determining component size
			
		}catch(IOException ioe){
			if(ioe instanceof NotSerializableException){
				logger.error("*****************************************************************************************************************************");
				logger.error("* "+((NotSerializableException)ioe).getMessage()+ " is not serializable");
				logger.error("* This code execution won't work in cluster environment");
				logger.error("* Please correct "+ ((NotSerializableException)ioe).getMessage()+" to be serializable or not be included in the session serialization");
				logger.error("* This code execution won't work in cluster environment");
				logger.error("*****************************************************************************************************************************");
				logger.error("...unable to  serialize component !!!");
			}else{
				logger.error("...unable to  serialize component !!!",ioe);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.util.DesktopSerializationListener#didDeserialize(org.zkoss.zk.ui.Desktop)
	 */
	public void didDeserialize(Desktop desktop) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.util.DesktopSerializationListener#willSerialize(org.zkoss.zk.ui.Desktop)
	 */
	public void willSerialize(Desktop desktop) {
		printPageSize();
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.util.DesktopActivationListener#didActivate(org.zkoss.zk.ui.Desktop)
	 */
	public void didActivate(Desktop desktop) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.util.DesktopActivationListener#willPassivate(org.zkoss.zk.ui.Desktop)
	 */
	public void willPassivate(Desktop desktop) {
		printPageSize();
		
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.util.SessionActivationListener#didActivate(org.zkoss.zk.ui.Session)
	 */
	public void didActivate(Session session) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.util.SessionActivationListener#willPassivate(org.zkoss.zk.ui.Session)
	 */
	public void willPassivate(Session session) {
		printPageSize();
		
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.util.SessionSerializationListener#willSerialize(org.zkoss.zk.ui.Session)
	 */
	public void willSerialize(Session session) {
		// TODO Auto-generated method stub
		
	}
	
	public void sessionWillPassivate(Desktop desktop) {
		super.sessionWillPassivate(desktop);
		printPageSize();
	}
}

