package com.ascepionpharm.lims.command.chemistry;

import java.util.*;
import java.io.File;

import com.ascepionpharm.lims.entity.chemistry.*;
import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.repository.chemistry.*;
import com.ascepionpharm.lims.repository.core.*;
import com.ascepionpharm.lims.universal.*;
import com.sun.java_cup.internal.internal_error;

/**
 * CreateReactionCommand: performs all actions to createreaction.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class CreateReactionCommand extends LIMSCommand {
	public CreateReactionCommand(String next) {
		this.next = next;
		this.name = "CreateReactionCommand";
	}

	public void performTask() throws CommandException {
		ReactionRepository reactionRepos;
		ChemicalMolecularRepository chemicalMolRepos;
		ProjectReactionRepository projectReactionRepos;
		SynthesisRouteRepository synthesisRouteRepos;

		Closable[] closableConnections;
		Collection connections = new ArrayList();

		try {
			reactionRepos = new ReactionRepository(conn);
			chemicalMolRepos = new ChemicalMolecularRepository(conn);
			projectReactionRepos = new ProjectReactionRepository(conn);
			synthesisRouteRepos = new SynthesisRouteRepository(conn);

			connections.add(reactionRepos);
			connections.add(chemicalMolRepos);
			connections.add(projectReactionRepos);
			connections.add(synthesisRouteRepos);

			closableConnections = (Closable[]) connections
					.toArray(new Closable[0]);
		} catch (RepositoryException re) {
			throw new CommandException(name + "Error making db connections. "
					+ re.getMessage());
		}
		try {

			ReactionBean reaction = new ReactionBean();
			ProjectReactionBean pr = new ProjectReactionBean();
			String reactionName;
			int product = Integer.parseInt(req.getParameter("hproduct").trim());
			int reactiona = Integer.parseInt(req.getParameter("hreactiona")
					.trim());
			int reactionb;
			int reactionc;
			int catalysta;
			String catalystb;
			String condition;
			if (req.getParameter("hreactionb") == null
					|| "".equals(req.getParameter("hreactionb").trim())) {
				reactionName = req.getParameter("nreactiona").trim()
						+ req.getParameter("nproduct").trim();
			} else {
				reactionName = req.getParameter("nreactiona").trim()
						+ req.getParameter("nreactionb").trim()
						+ req.getParameter("nproduct").trim();
			}
			if (req.getParameter("reactionc") == null
					|| "".equals(req.getParameter("reactionc").trim())) {
				reactionc = -1;
			} else {
				reactionc = Integer.parseInt(req.getParameter("reactionc")
						.trim());
			}
			if (req.getParameter("hreactionb") == null
					|| "".equals(req.getParameter("hreactionb").trim())) {
				reactionb = -1;
			} else {
				reactionb = Integer.parseInt(req.getParameter("hreactionb")
						.trim());
			}
			if (req.getParameter("hcatalysta") == null
					|| "".equals(req.getParameter("hcatalysta").trim())) {
				catalysta = -1;
			} else {
				catalysta = Integer.parseInt(req.getParameter("hcatalysta")
						.trim());
			}
			if (req.getParameter("catalystb") == null
					|| "".equals(req.getParameter("catalystb").trim())) {
				catalystb = "";
			} else {
				catalystb = req.getParameter("catalystb").trim();
			}
			if (req.getParameter("condition") == null
					|| "".equals(req.getParameter("condition").trim())) {
				condition = "";
			} else {
				condition = req.getParameter("condition").trim();
			}

			int projectId = Integer.parseInt(req.getParameter("projectId")
					.trim());
			reaction.setName(reactionName);
			reaction.setProduct(product);
			reaction.setReaction_a(reactiona);
			reaction.setReaction_b(reactionb);
			reaction.setReaction_c(reactionc);
			reaction.setCatalyst_a(catalysta);
			reaction.setCatalyst_b(catalystb);
			reaction.setConditions(condition);

			ProjectReactionBean[] projectReactions = (ProjectReactionBean[]) projectReactionRepos
					.getList(projectId);
			ReactionBean[] reactions = (ReactionBean[]) reactionRepos
					.checkUnique(reaction);
			int flag = 0;
			synchronized (conn) {
				if (reactions.length > 0) {
					for (int j = 0; j < projectReactions.length; j++) {
						if (flag > 0)
							break;
						for (int i = 0; i < reactions.length; i++) {
							if (flag > 0)
								break;
							if (projectReactions[j].getReaction_id() == reactions[i]
									.getReaction_id()) {
								if (reactions[i].getReaction_b() == reactionb
										&& reactions[i].getCatalyst_a() == catalysta) {
									req.setAttribute("myMessage",
													"Reaction has existed in this project");
									flag++;
								}
							}
						}
					}
					if (flag == 0) {
						reactionRepos.put(reaction);
						int reactionId = reactionRepos.getLastId();
						pr.setProject_id(projectId);
						pr.setReaction_id(reactionId);
						projectReactionRepos.put(pr);

						if (reactiona > 0) {
							synthesisRouteRepos.insertLN(reactionId, reactiona);
						}

						if (reactionb > 0) {
							synthesisRouteRepos.insertLN(reactionId, reactionb);
						}

						req.setAttribute("myMessage",
								"Reaction has been created successfully");
					}
				} else {
					reactionRepos.put(reaction);
					int reactionId = reactionRepos.getLastId();
					pr.setProject_id(projectId);
					pr.setReaction_id(reactionId);
					projectReactionRepos.put(pr);

					if (reactiona > 0) {
						synthesisRouteRepos.insertLN(reactionId, reactiona);
					}

					if (reactionb > 0) {
						synthesisRouteRepos.insertLN(reactionId, reactionb);
					}

					req.setAttribute("myMessage",
							"Reaction has been created successfully");
				}
			}

		} catch (RepositoryException re) {
			throw new CommandException(re.getMessage());
		} finally {
			callClosable(closableConnections);
		}
	}
}
