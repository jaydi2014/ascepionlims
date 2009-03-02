package com.ascepionpharm.lims.entity.chemistry;

import com.ascepionpharm.lims.universal.*;

import java.io.*;
import java.sql.Date;

/**
 * ExperimentBean: stores records from EXPERIMENT table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class ExperimentBean extends Item implements Serializable{
	 private int experiment_id;
	 private String name; 
	 private Date date;
	 private int temperature; 
	 private int humidity; 
	 private int reaction_id; 
	 private Object rawRatio;
	 private String process;
	 private String comment;
	 private String result;
	 
	public int getExperiment_id() {
		return experiment_id;
	}
	public void setExperiment_id(int experiment_id) {
		this.experiment_id = experiment_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public int getReaction_id() {
		return reaction_id;
	}
	public void setReaction_id(int reaction_id) {
		this.reaction_id = reaction_id;
	}
	public Object getRawRatio() {
		return rawRatio;
	}
	public void setRawRatio(Object rawRatio) {
		this.rawRatio = rawRatio;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	 
}
