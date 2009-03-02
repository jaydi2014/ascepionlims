package com.ascepionpharm.lims.command.chemistry;

import java.util.*;
import java.sql.Date;

import com.ascepionpharm.lims.entity.chemistry.ChemicalMolecularBean;
import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.repository.chemistry.ChemicalMolecularRepository;
import com.ascepionpharm.lims.repository.core.*;
import com.ascepionpharm.lims.universal.*;

/**
 * PurchaseRequestCommand: performs all actions to quarypurchase.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class QuaryChemicalMolecularCommand extends LIMSCommand {
	public QuaryChemicalMolecularCommand(String next) {
		this.next = next;
		this.name = "QuaryChemicalMolecularCommand";
	}

	public void performTask() throws CommandException {
		ChemicalMolecularRepository chemicalMolecularRepo;

		Closable[] closableConnections;
		Collection connections = new ArrayList();

		try {
			chemicalMolecularRepo = new ChemicalMolecularRepository(conn);
			connections.add(chemicalMolecularRepo);

			closableConnections = (Closable[]) connections
					.toArray(new Closable[0]);
		} catch (RepositoryException re) {
			throw new CommandException(name + "Error making db connections. "
					+ re.getMessage());
		}
		String way = req.getParameter("way");
		try {
			if(way.equals("innerName")){
				String innerName = req.getParameter("innerName").trim().toUpperCase();
				//String innerNameLike = "%" + innerName + "%";
				ChemicalMolecularBean[] chemicalMolecular = (ChemicalMolecularBean[])chemicalMolecularRepo.getByInnerName(innerName);
				req.setAttribute("chemicalMolecular",chemicalMolecular);
			}
			if(way.equals("chemicalName")){
				String chemicalName = req.getParameter("chemicalName").trim();
				//String chemicalNameLike = "%" + chemicalName + "%";
				ChemicalMolecularBean[] chemicalMolecular = (ChemicalMolecularBean[])chemicalMolecularRepo.getByChemicalName(chemicalName);
				req.setAttribute("chemicalMolecular",chemicalMolecular);
			}
			if(way.equals("smiles")){
				String smiles = req.getParameter("smiles").trim();
				//String smilesLike = "%" + smiles + "%";
				ChemicalMolecularBean[] chemicalMolecular = (ChemicalMolecularBean[])chemicalMolecularRepo.getBySmiles(smiles);
				req.setAttribute("chemicalMolecular",chemicalMolecular);
			}
			if(way.equals("molecularFormula")){
				String molecularFormula = req.getParameter("molecularFormula").trim();
				//String molecularFormulaLike = "%" + molecularFormula + "%";
				ChemicalMolecularBean[] chemicalMolecular = (ChemicalMolecularBean[])chemicalMolecularRepo.getByFormula(molecularFormula);
				req.setAttribute("chemicalMolecular",chemicalMolecular);
			}
		

		} catch (RepositoryException re) {
			throw new CommandException(re.getMessage());
		} finally {
			callClosable(closableConnections);
		}
	}
}
