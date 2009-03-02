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

public class ProjectChemicalMolecularBean extends Item implements Serializable{
	 private int project_id; 
	 private int chemicalMolecular_id;
	 
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public int getChemicalMolecular_id() {
		return chemicalMolecular_id;
	}
	public void setChemicalMolecular_id(int chemicalMolecular_id) {
		this.chemicalMolecular_id = chemicalMolecular_id;
	} 
}
