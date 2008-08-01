package com.ascepionpharm.lims.entity.core;

import java.io.Serializable;

import com.ascepionpharm.lims.universal.*;

/**
 * PermissionBean: stores records from LIMS_PAGE table.
 *
 * @author Li Jun Mulin
 * @version 
 *
 */

public class PageBean extends Item implements Serializable{
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
