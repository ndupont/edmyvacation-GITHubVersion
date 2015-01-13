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

package com.ellisdon.portal.mv.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.TableGenerator;

/**
 * This Class is a Data Transfer Object for table VacationHistory, use to transfer data between 
 * different application architecture layers e.g. Controller, Service, DAO etc  
 * 
 * @author ahmedb
 * @version 1.0, Nov 28, 2010
 * @since JDK1.6
 */

@Entity
@Table(name = "users_vacation_history")
public class VacationHistoryDTO {
	

	/*
    @GeneratedValue(strategy=GenerationType.TABLE, generator="UserVacationHist_SEQ")
	@TableGenerator(name="UserVacationHist_SEQ", table="SEQUENCE_TABLE", pkColumnName="SEQ_NAME",
                    valueColumnName="SEQ_COUNT", pkColumnValue="UserVacationHist_SEQ")
    */                
	@Id
	@Column(name = "iduservacation", unique=true, nullable=false)
	private int uservacationid;

	@Column(name = "userid")
	private int userid;
	
	@Column(name = "fromdate")
	private Date fromdate;

	@Column(name = "todate")
	private Date todate;
	
	@Column(name = "daystaken")
	private float daystaken;
	
	@Column(name = "daysnottaken")
	private float daysnottaken;
	
	@Column(name = "comments")
	private String comments;
	
	/**
	 * @return the fromdate
	 */
	public Date getFromdate() {
		return fromdate;
	}

	/**
	 * @param fromdate the fromdate to set
	 */
	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	/**
	 * @return the todate
	 */
	public Date getTodate() {
		return todate;
	}

	/**
	 * @param todate the todate to set
	 */
	public void setTodate(Date todate) {
		this.todate = todate;
	}

	/**
	 * @return the daystaken
	 */
	public float getDaystaken() {
		return daystaken;
	}

	/**
	 * @param daystaken the daystaken to set
	 */
	public void setDaystaken(float daystaken) {
		this.daystaken = daystaken;
	}

	/**
	 * @return the daysnottaken
	 */
	public float getDaysnottaken() {
		return daysnottaken;
	}

	/**
	 * @param daysnottaken the daysnottaken to set
	 */
	public void setDaysnottaken(float daysnottaken) {
		this.daysnottaken = daysnottaken;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the uservacationid
	 */
	public int getUservacationid() {
		return uservacationid;
	}

	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "com.ellisdon.portal.mv.dto.VacationHistoryDTO[ID=" + this.uservacationid + "]";
	}
	
}
