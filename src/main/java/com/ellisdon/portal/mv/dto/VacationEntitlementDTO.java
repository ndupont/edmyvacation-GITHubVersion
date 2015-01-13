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

import javax.persistence.*;

/**
 * This Class is a Data Transfer Object for table VacationEntitlement, use to transefer data between 
 * different application architecture layers e.g. Controller, Service, DAO etc  
 *
 * @author ahmedb
 * @version 1.0, Nov 28, 2010
 * @since JDK1.6
 */

@Entity
@Table(name = "users_vacation_entitlement")
public class VacationEntitlementDTO {

	@Id
	@Column(name = "iduserentitlement")
	private int userentitlementid;

	@Column(name = "userid")
	private int userid;

	@Column(name = "daysentitle")
	private int entitledays;

	@Column(name = "carryover")
	private float carryoverdays;
	
	@Column(name = "lastupdated")
	private Date lastupdated;
		
	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getUserentitlementid() {
		return userentitlementid;
	}

	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}

	/**
	 * @return the entitledays
	 */
	public int getEntitledays() {
		return entitledays;
	}

	/**
	 * @param entitledays the entitledays to set
	 */
	public void setEntitledays(int entitledays) {
		this.entitledays = entitledays;
	}

	public float getCarryoverdays() {
		return carryoverdays;
	}

	public void setCarryoverdays(float carryoverdays) {
		this.carryoverdays = carryoverdays;
	}
	
	/**
	 * @return the lastupdated
	 */
	public Date getLastupdated() {
		return lastupdated;
	}

	/**
	 * @param lastupdated the lastupdated to set
	 */
	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

	@Override
	public String toString() {
		return "com.ellisdon.portal.mv.dto.VacationEntitlementDTO[ID=" + this.userid + "]";
	}
	
}
