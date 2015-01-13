package com.ellisdon.portal.mv.dto;

import javax.persistence.*;

/**
 * This Class is a Data Transfer Object for table ed_user, use to transfer data between 
 * different application architecture layers e.g. Controller, Service, DAO etc  
 *
 * @author ahmedb
 * @version 1.0, Nov 08, 2011
 * @since JDK1.6
 */

@Entity
@Table(name = "ed_user")
public class UserDTO {

	@Id
	@Column(name = "UserID")
	private int userid;

	@Column(name = "FirstName")
	private String fname;

	@Column(name = "LastName")
	private String lname;

	@Column(name = "EmailAddress")
	private String email;
	
	@Column(name = "Password")
	private String password;

	public int getUserid() {
		return userid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	
}
