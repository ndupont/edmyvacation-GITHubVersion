package com.ellisdon.portal.mv.dto;

import javax.persistence.*;

/**
 * This Class is a Data Transfer Object for table cnf_customer, use to transfer data between 
 * different application architecture layers e.g. Controller, Service, DAO etc  
 *
 * @author ahmedb
 * @version 1.0, Nov 08, 2011
 * @since JDK1.6
 */

@Entity
@Table(name = "cnf_customer")
public class CustomerDTO {

	@Id
	@Column(name = "CustomerID")
	private int customerid;

	@Column(name = "Name")
	private String customername;

	@Column(name = "Domain")
	private String customerdomain;

	public int getCustomerid() {
		return customerid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCustomerdomain() {
		return customerdomain;
	}

	public void setCustomerdomain(String customerdomain) {
		this.customerdomain = customerdomain;
	}
	
	
}
