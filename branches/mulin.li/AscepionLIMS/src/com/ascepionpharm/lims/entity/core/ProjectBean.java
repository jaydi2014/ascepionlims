package com.ascepionpharm.lims.entity.core;

import com.ascepionpharm.lims.universal.*;

import java.io.*;
import java.sql.Date;


/**
 * EmployeeBean: stores records from PROJECT table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */
public class ProjectBean extends Item implements Serializable{
	private int departmentId;
	private String name;
	private String description;
	private String members;
	private Date start_time;
	private Date end_time;
	private int status;
	
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMembers() {
		return members;
	}
	public void setMembers(String members) {
		this.members = members;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
