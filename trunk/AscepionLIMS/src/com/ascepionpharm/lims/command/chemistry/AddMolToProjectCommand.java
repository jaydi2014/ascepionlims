package com.ascepionpharm.lims.command.chemistry;

import java.util.*;
import java.io.File;
import java.sql.Date;

import com.ascepionpharm.lims.entity.chemistry.ChemicalMolecularBean;
import com.ascepionpharm.lims.entity.chemistry.ProjectChemicalMolecularBean;
import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.repository.chemistry.*;
import com.ascepionpharm.lims.repository.core.*;
import com.ascepionpharm.lims.universal.*;

/**
 * AddMolToProjectCommand: performs all actions to addmol.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class AddMolToProjectCommand extends LIMSCommand {
	public AddMolToProjectCommand(String next) {
		this.next = next;
		this.name = "AddMolToProjectCommand";
	}

	public void performTask() throws CommandException {
		ChemicalMolecularRepository chemicalMolecularRepo;
		ProjectChemicalMolecularRepository projectChemicalMolecularRepo;

		Closable[] closableConnections;
		Collection connections = new ArrayList();

		try {
			chemicalMolecularRepo = new ChemicalMolecularRepository(conn);
			projectChemicalMolecularRepo = new ProjectChemicalMolecularRepository(
					conn);
			connections.add(chemicalMolecularRepo);
			connections.add(projectChemicalMolecularRepo);

			closableConnections = (Closable[]) connections
					.toArray(new Closable[0]);
		} catch (RepositoryException re) {
			throw new CommandException(name + "Error making db connections. "
					+ re.getMessage());
		}
		try {
			String innerName = req.getParameter("inputInnerName").trim()
					.toUpperCase();
			if (chemicalMolecularRepo.checkInnerName(innerName).equals(
					"no find")) {
				req.setAttribute("myMessage", "No this molecular in database");
			} else {
				int molId = ((ChemicalMolecularBean[]) chemicalMolecularRepo
						.getByInnerName(innerName))[0].getId();
				int projectId = Integer.parseInt(req
						.getParameter("projectSingleSelect"));
				if (projectChemicalMolecularRepo.get(projectId, molId) != null) {
					req.setAttribute("myMessage",
							"This molecular has existed in the project");
				} else {
					ProjectChemicalMolecularBean pm = new ProjectChemicalMolecularBean();
					pm.setChemicalMolecular_id(molId);
					pm.setProject_id(projectId);
					projectChemicalMolecularRepo.put(pm);

					String userName = req.getParameter("userName");
					if (userName != null && !"".equals(userName)) {
						String logcontext = userName + " add molecular_"
								+ molId + " to project_" + projectId;
						File file = new File("c:\\log\\LIMS_Login.log");
						FileFeeder.writeFile(file, logcontext);
					}

					req.setAttribute("myMessage",
							"Molecular has been added to project successfully");
				}
			}

		} catch (RepositoryException re) {
			throw new CommandException(re.getMessage());
		} finally {
			callClosable(closableConnections);
		}
	}
}
