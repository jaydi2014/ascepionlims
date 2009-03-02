package com.ascepionpharm.lims.entity.chemistry;

import com.ascepionpharm.lims.universal.*;

import java.io.*;

/**
 * ChemicalFileBean: stores records from CHEMICALFILE table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class ChemicalFileBean extends Item implements Serializable{
	String fileName;
	File fileContext;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public File getFileContext() {
		return fileContext;
	}
	public void setFileContext(File fileContext) {
		this.fileContext = fileContext;
	}
}
