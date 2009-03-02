package com.ascepionpharm.lims.command.chemistry;

import java.util.ArrayList;
import java.util.Collection;

import com.ascepionpharm.lims.entity.chemistry.ChemicalMolecularBean;
import com.ascepionpharm.lims.repository.chemistry.ChemicalFileRepository;
import com.ascepionpharm.lims.repository.chemistry.ChemicalMolecularRepository;
import com.ascepionpharm.lims.universal.Closable;
import com.ascepionpharm.lims.universal.CommandException;
import com.ascepionpharm.lims.universal.LIMSCommand;
import com.ascepionpharm.lims.universal.RepositoryException;



public class DownloadFileCommand extends LIMSCommand{
	public DownloadFileCommand(String next) {
		this.next = next;
		this.name = "DownloadFileCommand";
	}

	public void performTask() throws CommandException {
		
		ChemicalMolecularRepository chemicalMolecularRepo;
		ChemicalFileRepository chemicalFileRepo;
		Closable[] closableConnections;
		Collection<ChemicalMolecularRepository> connections = new ArrayList<ChemicalMolecularRepository>();

		try {
			chemicalMolecularRepo = new ChemicalMolecularRepository(conn);
			chemicalFileRepo = new ChemicalFileRepository(conn);
			connections.add(chemicalMolecularRepo);

			closableConnections = (Closable[]) connections
					.toArray(new Closable[0]);
		} catch (RepositoryException re) {
			throw new CommandException(name + "Error making db connections. "
					+ re.getMessage());
		}
		
		try {
			String idd = req.getParameter("hiddenID");
			chemicalFileRepo.get(Integer.parseInt(idd));
			ChemicalMolecularBean[] chemicalMolecular=(ChemicalMolecularBean[])req.getSession().getAttribute("chemicalMolecularTemp");
			if(chemicalMolecular!=null){
			    req.setAttribute("chemicalMolecular",chemicalMolecular);
			}
		} catch (Exception re) {
			throw new CommandException(re.getMessage());
		} finally {
			callClosable(closableConnections);
		}
	}
}
