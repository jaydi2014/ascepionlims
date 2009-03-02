package com.ascepionpharm.lims.entity.chemistry;

import com.ascepionpharm.lims.universal.*;

import java.io.*;

/**
 * ChemicalMolecularBean: stores records from CHEMICALMOLECULAR table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class ChemicalMolecularBean extends Item implements Serializable{
	String innerName;
	String chemicalName;
	String smiles;
	String relatedFiles;
	String molecularFormula;
	
	public String getMolecularFormula() {
		return molecularFormula;
	}
	public void setMolecularFormula(String molecularFormula) {
		this.molecularFormula = molecularFormula;
	}
	public String getInnerName() {
		return innerName;
	}
	public void setInnerName(String innerName) {
		this.innerName = innerName;
	}
	public String getChemicalName() {
		return chemicalName;
	}
	public void setChemicalName(String chemicalName) {
		this.chemicalName = chemicalName;
	}
	public String getSmiles() {
		return smiles;
	}
	public void setSmiles(String smiles) {
		this.smiles = smiles;
	}
	public String getRelatedFiles() {
		return relatedFiles;
	}
	public void setRelatedFiles(String relatedFiles) {
		this.relatedFiles = relatedFiles;
	}
}
