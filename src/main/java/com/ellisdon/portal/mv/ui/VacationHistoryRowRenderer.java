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

package com.ellisdon.portal.mv.ui;

import static org.zkoss.zk.ui.event.Events.ON_CLICK;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Group;
import org.zkoss.zul.Groupfoot;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import com.ellisdon.portal.mv.dto.VacationHistoryDTO;
import com.ellisdon.portal.mv.model.UserVacationBean;
import com.ellisdon.portal.mv.util.CommanConstants;
import com.ellisdon.portal.mv.util.Utility;

/**
 * This class is the RowRenderer implementation for Vacation History Grid 
 *
 * @author ahmedb
 * @version 1.0, Dec 8, 2010
 * @since JDK1.6
 */
public class VacationHistoryRowRenderer implements RowRenderer {

	private UserVacationBean userVacation;
	
	public VacationHistoryRowRenderer(UserVacationBean userVacation){
		this.userVacation = userVacation;
	}
	
	//@Override
	public void render(Row row, Object data) throws Exception {

		final VacationHistoryDTO vachistdto;
		if (row instanceof Group) {
	        row.appendChild(new Label(data.toString()));
	    }
        else {
        	vachistdto = (VacationHistoryDTO) data;
			String fromDate = Utility.convertDate2String(vachistdto.getFromdate(), CommanConstants.DATE_FORMAT_DISPLAY);
			row.appendChild(new Label("" + fromDate));
			String toDate = Utility.convertDate2String(vachistdto.getTodate(), CommanConstants.DATE_FORMAT_DISPLAY);
			row.appendChild(new Label("" + toDate));        			
		    row.appendChild(new Label("" + vachistdto.getDaystaken()));
		    row.appendChild(new Label("" + vachistdto.getDaysnottaken()));
		    row.appendChild(new Label("" + vachistdto.getComments()));    
			
			Button button = new Button("Not Taken");
			row.appendChild(button);
						
			if (vachistdto.getDaystaken() == 0 ){
				button.setDisabled(true);
			}

  			button.addEventListener(ON_CLICK, new EventListener() {
				public void onEvent(Event event) throws Exception {
					try{
						userVacation.cancelUserVacation(vachistdto);
					}catch(Exception e){
						//errorLb.setValue(e.getMessage());
					}
				}
			});		
        }		
	}



}
