package com.ascepionpharm.lims.entity.core;

import com.ascepionpharm.lims.universal.*;

import java.io.*;

/**
 * PermissionBean: stores records from PERMISSIONS table.
 *
 * @author Li Jun Mulin
 * @version 
 *
 */

public class PermissionBean extends Item implements Serializable {      
	private int 	employeeTypeId;
	private int		pageId;
	private String	uri;
	private int		granted;
		
	public PermissionBean(int id) {
		this.employeeTypeId = id;
		this.pageId = 0;
		this.uri = "";
		this.granted = 0;
	}
	
	public PermissionBean () {
		this.employeeTypeId = 0;
		this.pageId = 0;		
		this.uri = "";
		this.granted = 0;		
	}								

	public void setURI (String uri) {
		this.uri = uri;
	}
	
	public String getURI () {
		return this.uri;
	}
	
	public void setGranted (int granted) {
		this.granted = granted;
	}
	
	public int getGranted () {
		return this.granted;
	}					
	
	public void setEmployeeTypeId (int employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}
	
	public int getEmployeeTypeId () {
		return this.employeeTypeId;
	}


	public void setPageId (int pageId) {
		this.pageId = pageId;
	}
	
	public int getPageId () {
		return this.pageId;
	}
}