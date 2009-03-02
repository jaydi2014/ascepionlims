package com.ascepionpharm.lims.entity.chemistry;

import com.ascepionpharm.lims.universal.*;

import java.io.*;

/**
 * ProjectChemicalMolecularBean: stores records from Project_ChemicalMolecular table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class ProjectReactionBean extends Item implements Serializable{
	 private int project_id; 
	 private int reaction_id;
	 
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public int getReaction_id() {
		return reaction_id;
	}
	public void setReaction_id(int reaction_id) {
		this.reaction_id = reaction_id;
	}

}
