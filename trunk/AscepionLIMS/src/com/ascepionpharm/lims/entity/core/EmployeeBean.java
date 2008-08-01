package com.ascepionpharm.lims.entity.core;

import com.ascepionpharm.lims.universal.*;

import java.io.*;
import java.sql.Date;


/**
 * EmployeeBean: stores records from EMPLOYEE table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */
public class EmployeeBean extends Item implements Serializable {
	private String employeeNumber;
	private String name;
	private String username;
	private String password;
	private int active;
	private int age;
	private String sex;
	private Date brith;
	private String identity;
	private String nativeplace;
	private String degree;
	private String school;
	private Date pactStartTime;
	private Date pactEndTime;
	private Date entranceTime;
	private Date leaveTime;
	private String party;
	private String recordplace;
	private String commentline;
	private String email;
	private String labPhone;
	private String mobilePhone;
	private String title;
	private int supervisorId;
	private String navisionCode;
	private String init;
	private int departmentId;
	
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBrith() {
		return brith;
	}
	public void setBrith(Date brith) {
		this.brith = brith;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getNativeplace() {
		return nativeplace;
	}
	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public Date getPactStartTime() {
		return pactStartTime;
	}
	public void setPactStartTime(Date pactStartTime) {
		this.pactStartTime = pactStartTime;
	}
	public Date getPactEndTime() {
		return pactEndTime;
	}
	public void setPactEndTime(Date pactEndTime) {
		this.pactEndTime = pactEndTime;
	}
	public Date getEntranceTime() {
		return entranceTime;
	}
	public void setEntranceTime(Date entranceTime) {
		this.entranceTime = entranceTime;
	}
	public Date getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getRecordplace() {
		return recordplace;
	}
	public void setRecordplace(String recordplace) {
		this.recordplace = recordplace;
	}
	public String getCommentline() {
		return commentline;
	}
	public void setCommentline(String commentline) {
		this.commentline = commentline;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLabPhone() {
		return labPhone;
	}
	public void setLabPhone(String labPhone) {
		this.labPhone = labPhone;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}
	public String getNavisionCode() {
		return navisionCode;
	}
	public void setNavisionCode(String navisionCode) {
		this.navisionCode = navisionCode;
	}
	public String getInit() {
		return init;
	}
	public void setInit(String init) {
		this.init = init;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}



}