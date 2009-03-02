package com.ascepionpharm.lims.entity.chemistry;

import com.ascepionpharm.lims.universal.*;

import java.io.*;

/**
 * ExperimentFileBean: stores records from ExperimentFile table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class ExperimentFileBean extends Item implements Serializable{
	 private int file_id;
	 private int experiment_id;
	 private String fileName;
	 private File fileContext;
	 
	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	public int getExperiment_id() {
		return experiment_id;
	}
	public void setExperiment_id(int experiment_id) {
		this.experiment_id = experiment_id;
	}
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
