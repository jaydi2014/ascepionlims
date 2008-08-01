package com.ascepionpharm.lims.entity.core;

import java.io.Serializable;

import com.ascepionpharm.lims.universal.*;

/**
 * UserBean: stores records from LIMS_USER table.
 *
 * @author Li Jun Mulin
 * @version 
 *
 */

public class UserBean extends Item implements Serializable{
	private int userNumber;
	private String name;
	private String username;
	private String password;
	private int active;
	private int departmentId;
	
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
}
