package com.ascepionpharm.lims.entity.core;

import com.ascepionpharm.lims.universal.*;

import java.io.*;
import java.sql.Date;


/**
 * EmployeeBean: stores records from ACCOUNTINGITEM table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */
public class AccountingItemBean extends Item implements Serializable{
	private String name;
	private String commentline;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCommentline() {
		return commentline;
	}
	public void setCommentline(String commentline) {
		this.commentline = commentline;
	}
	
}
