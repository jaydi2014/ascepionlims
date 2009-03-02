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
 * QueryReactionCommand: performs all actions to queryreaction.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class QueryReactionCommand extends LIMSCommand {
	public QueryReactionCommand(String next) {
		this.next = next;
		this.name = "QueryReactionCommand";
	}

	public void performTask() throws CommandException {
		ChemicalMolecularRepository chemicalMolecularRepo;
		ReactionRepository reactionRepo;

		Closable[] closableConnections;
		Collection connections = new ArrayList();

		try {
			chemicalMolecularRepo = new ChemicalMolecularRepository(conn);
			reactionRepo = new ReactionRepository(conn);
			connections.add(chemicalMolecularRepo);
			connections.add(reactionRepo);

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
				req.setAttribute("failure", "failure");
				req.setAttribute("myMessage", "No this molecular in database");
			} else {
				Item[] reactions = reactionRepo.getByInnerName("%"+innerName+"%");
				req.setAttribute("innerName", innerName);
				req.setAttribute("reactionQuery", reactions);
			}

		} catch (RepositoryException re) {
			throw new CommandException(re.getMessage());
		} finally {
			callClosable(closableConnections);
		}
	}
}
